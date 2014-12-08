package com.sunlights.common.cache;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import play.Logger;

/**
 * 支持注解式配置缓存
 *
 * Created by tangweiqun on 2014/12/6.
 */
public class CacheFactory {
    private static final  MethodHandler methodHandler = new CacheMethodHandler();

    private static final MethodFilter methodFilter = new CacheMethodFilter();

    public static<T> T getProxyCacheObject(Class<T> clazz) {
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(clazz);
        //设置过滤器，判断哪些方法调用需要被拦截
        factory.setFilter(methodFilter);
        Class<T> c = factory.createClass();
        T object = null;
        try {
            object= c.newInstance();
            ((Proxy)object).setHandler(methodHandler);
        } catch (Exception e) {
            Logger.error("缓存强转类型错误", e);
        }
        return object;
    }

}
