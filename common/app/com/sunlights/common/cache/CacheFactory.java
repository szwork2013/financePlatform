package com.sunlights.common.cache;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import play.Logger;
import play.cache.Cache;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支持注解式配置缓存
 *
 * Created by tangweiqun on 2014/12/6.
 */
public class CacheFactory {

    public static<T> T getProxyCacheObject(Class<T> clazz) {
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(clazz);
        //设置过滤器，判断哪些方法调用需要被拦截
        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                Cacheable cacheable = m.getAnnotation(Cacheable.class);
                if(cacheable != null) {
                    return true;
                }
                return false;
            }
        });
        Class<?> c = factory.createClass();
        MethodHandler mi = new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                //拦截后前置处理，改写name属性的内容
                //实际情况可根据需求修改
                Class<?> clazz = thisMethod.getReturnType();
                Cacheable cacheable = thisMethod.getAnnotation(Cacheable.class);
                String key = cacheable.key();
                int duration = cacheable.duration();
                StringBuilder sb = new StringBuilder();
                sb.append(key).append("-").append(self.getClass().getSimpleName()).append("-").append(thisMethod.getName()).append("-");
                if(args != null) {
                    for(Object obj : args) {
                        sb.append(obj.toString());
                    }
                }
                key = sb.toString();
                Object obj = Cache.get(key);
                if(obj != null) {
                    return clazz.cast(obj);
                } else {
                    Object objNew = proceed.invoke(self, args);
                    if(duration <= 0) {
                        Cache.set(key, objNew);
                    } else {
                        Cache.set(key, objNew, duration);
                    }
                    return clazz.cast(objNew);
                }
            }
        };
        T object = null;
        try {
            object=(T) c.newInstance();
            ((Proxy)object).setHandler(mi);
        } catch (Exception e) {
            Logger.error("缓存强转类型错误", e);
        }
        return object;
    }

}
