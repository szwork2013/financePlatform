package com.sunlights.common.cache;

import javassist.util.proxy.MethodHandler;
import play.cache.Cache;

import java.lang.reflect.Method;

/**
 * Created by tangweiqun on 2014/12/6.
 */
public class CacheMethodHandler implements MethodHandler {

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
}
