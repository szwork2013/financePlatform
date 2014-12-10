package com.sunlights.common.cache;

import javassist.util.proxy.MethodHandler;
import play.cache.Cache;

import java.lang.reflect.Method;

/**
 * 处理打了Cacheable注解的方法
 *
 * Created by tangweiqun on 2014/12/6.
 */
class CacheMethodHandler implements MethodHandler {

    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
        //拦截后前置处理，改写name属性的内容
        //实际情况可根据需求修改
        Class<?> clazz = thisMethod.getReturnType();
        Cacheable cacheable = thisMethod.getAnnotation(Cacheable.class);
        String key = cacheable.key();

        key = buildKey(self, thisMethod, args, key);
        Object obj = Cache.get(key);
        if(obj != null) {
            return clazz.cast(obj);
        } else {
            Object objNew = proceed.invoke(self, args);
            int duration = cacheable.duration();
            setDuration(key, duration, objNew);
            return clazz.cast(objNew);
        }
    }

    private String buildKey(Object self, Method thisMethod, Object[] args, String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(key).append("-").append(self.getClass().getSimpleName()).append("-").append(thisMethod.getName()).append("-");
        if(args != null) {
            for(Object obj : args) {
                sb.append(obj.toString());
            }
        }
        key = sb.toString();
        return key;
    }

    private void setDuration(String key, int duration, Object objNew) {
        if(duration <= 0) {
            Cache.set(key, objNew);
        } else {
            Cache.set(key, objNew, duration);
        }
    }
}
