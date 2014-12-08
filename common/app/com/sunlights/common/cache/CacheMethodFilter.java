package com.sunlights.common.cache;

import javassist.util.proxy.MethodFilter;

import java.lang.reflect.Method;

/**
 * 方法级别的过滤
 * 过滤掉没有打Cacheable的方法
 *
 * Created by tangweiqun on 2014/12/6.
 */
class CacheMethodFilter implements MethodFilter {

    @Override
    public boolean isHandled(Method method) {
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        return cacheable != null;
    }
}
