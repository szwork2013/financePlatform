package com.sunlights.core.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sunlights.common.AppConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConfigUtil;
import com.sunlights.core.service.AppVersionService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.F;
import util.JsonUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tangweiqun on 2015/3/12.
 */

public class AppVersionServiceImpl implements AppVersionService {
    private static final String ACCESS_TOKEN_URL_IOS = "https://itunes.apple.com/cn/lookup?id=948242790";

    private static final String VERSION = "version";

    private static final String RESULT_KEY = "results";

    private Cache<String, String> cacheFormCallable = null;

    private static final AppVersionServiceImpl instance = new AppVersionServiceImpl();

    private AppVersionServiceImpl() {
        try {
            cacheFormCallable = createCallableCache();

        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    public static final AppVersionServiceImpl getInstance() {
        return instance;
    }

    /**
     * 创建一个缓存对象
     *
     * @throws Exception
     */
    private static Cache<String, String> createCallableCache() throws Exception {
        Cache<String, String> cache = CacheBuilder
                .newBuilder()
                .maximumSize(1)
                .expireAfterWrite(359, TimeUnit.MINUTES)
                .build();
        return cache;
    }

    @Override
    public String getLatestVersionFromAppStore(final String platform) {
        CommonUtil.checkPlatform(platform);
        try {
            //Callable只有在缓存值不存在时，才会调用
            return cacheFormCallable.get(VERSION + platform, new Callable<String>() {
                @Override
                public String call() throws Exception {
                    if(AppConst.PLATFORM_IOS.equals(platform)) {
                        return "1.7";
                        //return getValueByHttpClient(ACCESS_TOKEN_URL_IOS, VERSION);
                    } else if(AppConst.PLATFORM_ANDROID.equals(platform)) {
                        ParameterService parameterService = new ParameterService();
                        return parameterService.getParameterByName(AppConst.ANDROID_LATEST_VERSION);
                    }
                    return null;
                }
            });
        } catch (ExecutionException e) {
            Logger.error("获取最新版本失败", e);
            return null;
        }
    }

    @Override
    public String refresh(String platform) {
        cacheFormCallable.cleanUp();

        return getLatestVersionFromAppStore(platform);
    }

    private String getValueByHttpClient(String url, String keyName) throws IOException {
        //获得HttpResponse实例
        HttpClient client = new HttpClient();
        ConfigUtil.setProxy(client);
        GetMethod getMethod = new GetMethod(url);

        int statusCode = client.executeMethod(getMethod);
        String version = "";
        Logger.debug("调用app最新版本接口返回statusCode：" + statusCode);
        //判断是否请求成功
        if (statusCode == 200) {
            //获得返回结果
            String result = getMethod.getResponseBodyAsString();
            //解析json数据
            Map map = JsonUtil.jsonToMap(result);
            Object value = map.get(RESULT_KEY);
            if (value != null) {
                List list = (List)value;
                Map<String, String> results = (Map<String, String>)list.get(0);
                version = results.get(VERSION);
            }
        }
        return version;
    }
}
