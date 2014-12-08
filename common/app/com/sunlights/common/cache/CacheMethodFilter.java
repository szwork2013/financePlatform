package com.sunlights.common.cache;

import javassist.util.proxy.MethodFilter;

import java.lang.reflect.Method;

/**
 * Created by tangweiqun on 2014/12/6.
 */
class CacheMethodFilter implements MethodFilter {

    @Override
    public boolean isHandled(Method method) {
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        if(cacheable != null) {
            return true;
        }
        return false;
    }
}
