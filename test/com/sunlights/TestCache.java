package com.sunlights;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import models.Activity;
import org.fest.assertions.Assertions;
import org.junit.Test;
import play.Logger;
import play.cache.Cache;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.WithApplication;

import java.util.List;

/**
 * Created by Administrator on 2014/12/5.
 */
public class TestCache extends WithApplication {
<<<<<<< HEAD

    private String title;
    private Activity activity;

    @Test
    public void testCache() {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                ActivityService activityService = new ActivityServiceImpl();
                List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                title = activities.get(0).getTitle();
                Logger.info("缓存到内存 = " + title);
                Cache.set(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE, activities);

                List<Activity> activitiesNew = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                String old = activitiesNew.get(0).getTitle();
                Logger.info("同一个事物里的值(不是缓存的) = " + old);
            }
        });

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                ActivityService activityService = new ActivityServiceImpl();

                List<Activity> activitiesNew = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                String old = activitiesNew.get(0).getTitle();
                Logger.info("不同事物里的值(不是缓存的) = " + old);

                List<Activity> activitiesLater = (List<Activity>) Cache.get(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                String titleLater = activitiesLater.get(0).getTitle();

                Logger.info(" : 从不同事物中的缓存中cache = " + titleLater);
                Assertions.assertThat(titleLater).isEqualTo(title);
            }
        });
=======
    private String title = "";
    Activity activity = null;
    @Test
    public void testCache() {

                JPA.withTransaction(new F.Callback0() {

                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = new ActivityServiceImpl();
                        List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        title = activities.get(0).getTitle();
                        Logger.info("缓存到内存 = " + title);
                        Cache.set(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE, activities);

                        List<Activity> activitiesNew = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String old = activitiesNew.get(0).getTitle();
                        Logger.info("同一个事物里的值(不是缓存的) = " + old);
                    }
                });

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = new ActivityServiceImpl();

                        List<Activity> activitiesNew = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String old = activitiesNew.get(0).getTitle();
                        Logger.info("不同事物里的值(不是缓存的) = " + old);

                        List<Activity> activitiesLater = (List<Activity>)Cache.get(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String titleLater = activitiesLater.get(0).getTitle();

                        Logger.info(" : 从不同事物中的缓存中cache = " + titleLater);
                        Assertions.assertThat(titleLater).isEqualTo(title);
                    }
                });

>>>>>>> master
    }

    @Test
    public void testCacheable() {

<<<<<<< HEAD
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                ActivityService activityService = ActivityServiceFactory.getActivityService();
                List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                activity = activities.get(0);
                title = activities.get(0).getTitle();
                Logger.info("缓存到内存场景活动 = " + title);
            }
        });

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {

                String sql = "update f_activity set title = '" + activity.getTitle() + "111" + "' where id = " + activity.getId();
                JPA.em().createNativeQuery(sql).executeUpdate();
            }
        });

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                ActivityService activityService = ActivityServiceFactory.getActivityService();
                List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                String titleCache = activities.get(0).getTitle();
                Logger.info("得到缓存的内容 = " + titleCache);

                Assertions.assertThat(titleCache).isEqualTo(title);

            }
        });

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {

                String sql = "update f_activity set title = '" + activity.getTitle() + "' where id = " + activity.getId();
                JPA.em().createNativeQuery(sql).executeUpdate();
            }
        });
=======

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = ActivityServiceFactory.getActivityService();
                        List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        activity = activities.get(0);
                        title = activities.get(0).getTitle();
                        Logger.info("缓存到内存场景活动 = " + title);


                    }
                });

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {

                        String sql = "update f_activity set title = '" + activity.getTitle() + "111" + "' where id = " + activity.getId();
                        JPA.em().createNativeQuery(sql).executeUpdate();
                    }
                });

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = ActivityServiceFactory.getActivityService();
                        List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String titleCache = activities.get(0).getTitle();
                        Logger.info("得到缓存的内容 = " + titleCache);

                        Assertions.assertThat(titleCache).isEqualTo(title);

                    }
                });

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {

                        String sql = "update f_activity set title = '" + activity.getTitle() + "' where id = " + activity.getId();
                        JPA.em().createNativeQuery(sql).executeUpdate();
                    }
                });

>>>>>>> master
    }

    @Test
    public void testCacheableSpeed() {
<<<<<<< HEAD
=======

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {

                        Long start = System.currentTimeMillis();
>>>>>>> master

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {

                Long start = System.currentTimeMillis();

                ActivityService activityService = ActivityServiceFactory.getActivityService();
                List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);

                Logger.info("花费时间：" + (System.currentTimeMillis() - start));

<<<<<<< HEAD
            }
        });

        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            JPA.withTransaction(new F.Callback0() {
                @Override
                public void invoke() throws Throwable {
                    ActivityService activityService = ActivityServiceFactory.getActivityService();
                    List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                }
            });
        }
        Logger.info("花费时间：" + (System.currentTimeMillis() - start));

=======
>>>>>>> master
    }


}