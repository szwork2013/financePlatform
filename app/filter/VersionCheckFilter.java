package filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.service.AppVersionService;
import com.sunlights.core.service.impl.AppVersionServiceImpl;
import org.apache.commons.lang3.StringUtils;
import play.Configuration;
import play.Logger;
import play.api.libs.iteratee.Done;
import play.api.libs.iteratee.Iteratee;
import play.api.mvc.EssentialAction;
import play.api.mvc.EssentialFilter;
import play.api.mvc.RequestHeader;
import play.api.mvc.Result;
import play.db.jpa.Transactional;

import static play.mvc.Results.ok;

/**
 * Created by tangweiqun on 2015/3/11.
 */
@Transactional
public class VersionCheckFilter implements EssentialFilter {

    private final AppVersionService appVersionService = AppVersionServiceImpl.getInstance();

    @Override
    public EssentialAction apply(final EssentialAction next) {
        return new JavaEssentialAction() {
            @Override
            public EssentialAction apply() {
                return next.apply();
            }

            @Override
            public Iteratee<byte[], Result> apply(RequestHeader rh) {
                String uri = rh.uri();

                Logger.debug("uri: = " + uri);

                if(UriFilterMap.isContain(uri)) {
                    return next.apply(rh);
                }

                String userAgent = rh.headers().get(AppConst.HEADER_USER_AGENT).get();

                if(StringUtils.isEmpty(userAgent)) {
                    Logger.debug("userAgent is null");
                    return next.apply(rh);
                }

                String clientVersion = CommonUtil.getCurrentVersionFromStr(userAgent);

                String platform = CommonUtil.getCurrentPlatformFromStr(userAgent);

                if (AppConst.PLATFORM_PC.equals(platform)) {
                    return next.apply(rh);
                }

                String latestVersion = appVersionService.getLatestVersionFromAppStore(platform);

                Logger.debug("userAgent == " + userAgent + " latestVersion = " + latestVersion + " platform = " + platform);



                //这个过滤器只适用于ios的
                if(AppConst.PLATFORM_IOS.equals(platform)) {

                    //TODO  为了支持临时的方案
                    boolean isUpdate = clientVersion.compareTo(Configuration.root().getString("ios.autoupdate.clientVersion")) <= 0
                            && latestVersion.compareTo(Configuration.root().getString("ios.autoupdate.appStoreversion")) >= 0;

                    Logger.debug("isUpdate = " + isUpdate);

                    if (isUpdate) {
                        JsonNode json = MessageUtil.getInstance().msgToJson(new Message(Severity.WARN, MsgCode.MUST_UPDATE_VERSION, latestVersion));
                        Logger.debug(">>信息：" + json.toString());
                        return Done.apply(ok(json).toScala(), null);
                    } else {
                        return next.apply(rh);
                    }
                } else {
                    return next.apply(rh);
                }
            }
        };
    }
}
