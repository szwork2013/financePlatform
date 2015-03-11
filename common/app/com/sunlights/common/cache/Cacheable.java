package com.sunlights.common.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个注解是方法级别的注解
 * 作用是：
 * 如果某个方法打上了这个注解的话，那么这个方法查出来的数据会缓存到缓存中，
 * 等待下次查询的时候则直接从缓存中出去数据
 * <p/>
 * 用法例子：
 * ActivityService activityService = ActivityServiceFactory.getActivityService();
 * List<Activity> activities = activityService.getActivityByScene("ASC002");
 * 在方法上getActivityByScene有注解
 *
 * @Cacheable(key="scene", duration = 150)：代表这个方法查出来的数据将会放到缓存中，key为 scene-ActivityServiceImpl-getActivityByScene-ASC002；在缓存中存在时间为150秒
 * <p/>
 * Created by tangweiqun on 2014/12/6.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {

    /**
     * The cache key to store the result in
     * 这个key不是真正在缓存中的key， 所以可以相同
     */
    String key();

    /**
     * The duration the action should be cached for.  Defaults to 0.
     * 如果是0的话则永久缓存，等待挤出
     */
    int duration() default 0;

}
