package com.sunlights.common.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tangweiqun on 2014/12/6.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {

    /**
     * The cache key to store the result in
     * 最好不要一样哟
     */
    String key();

    /**
     * The duration the action should be cached for.  Defaults to 0.
     * 如果是0的话则永久缓存，等待挤出
     */
    int duration() default 0;

}
