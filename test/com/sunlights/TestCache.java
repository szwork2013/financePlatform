package com.sunlights;

import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import models.Activity;
import models.RewardFlow;
import org.junit.Test;
import play.Logger;
import play.cache.Cache;
import play.db.jpa.JPA;
import play.libs.F;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/**
 * Created by Administrator on 2014/12/5.
 */
public class TestCache {

    @Test
    public void testCache() {

        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = new ActivityServiceImpl();
                        List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String title = activities.get(0).getTitle();
                        Logger.info("缓存到内存 = " + title);
                        Cache.set(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE, activities);
                        try{
                            TimeUnit.SECONDS.sleep(30);
                        } catch (Exception e) {

                        }

                        List<Activity> activitiesNew = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String old = activitiesNew.get(0).getTitle();
                        Logger.info("同一个事物里的值(不是缓存的) = " + old);

                        //List<Activity> activitiesLater = (List<Activity>)Cache.get(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        //String titleLater = activitiesLater.get(0).getTitle();

                       // Logger.info("cache = " + title + " : new = " + titleLater);
                    }
                });



                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = new ActivityServiceImpl();

                        //
                        try{
                            TimeUnit.SECONDS.sleep(30);
                        } catch (Exception e) {

                        }

                        List<Activity> activitiesNew = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String old = activitiesNew.get(0).getTitle();
                        Logger.info("不同事物里的值(不是缓存的) = " + old);

                        List<Activity> activitiesLater = (List<Activity>)Cache.get(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String titleLater = activitiesLater.get(0).getTitle();

                        Logger.info(" : 从不同事物中的缓存中cache = " + titleLater);
                    }
                });




            }
        });


    }

    @Test
    public void testCacheable() {

        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = ActivityServiceFactory.getActivityService();
                        List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);

                        List<String> titles = activityService.getActivityTitles("3333");

                        String title = activities.get(0).getTitle();
                        Logger.info("缓存到内存场景活动 = " + title);

                        Logger.info("缓存到内存根据prdCode获得title = " + titles.get(0));


                    }
                });
                try{
                    TimeUnit.SECONDS.sleep(30);
                } catch (Exception e) {

                }

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = ActivityServiceFactory.getActivityService();
                        List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);
                        String title = activities.get(0).getTitle();
                        Logger.info("得到缓存的内容 = " + title);

                        List<String> titles = activityService.getActivityTitles("3333");
                        Logger.info("缓存到内存根据prdCode获得title = " + titles.get(0));


                    }
                });



            }
        });
    }

    @Test
        public void testCacheableSpeed() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        try{
                            TimeUnit.SECONDS.sleep(30);
                        } catch (Exception e) {

                        }
                        Long start = System.currentTimeMillis();

                            ActivityService activityService = ActivityServiceFactory.getActivityService();
                            List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);

                        Logger.info("花费时间：" + (System.currentTimeMillis() - start));



                        //String title = activities.get(0).getTitle();
                        //Logger.info("缓存到内存场景活动 = " + title);

                        //Logger.info("缓存到内存根据prdCode获得title = " + titles.get(0));
                    }
                });


                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        try{
                            TimeUnit.SECONDS.sleep(30);
                        } catch (Exception e) {

                        }
                        Long start = System.currentTimeMillis();

                        ActivityService activityService = ActivityServiceFactory.getActivityService();
                        List<Activity> activities = activityService.getActivityByScene(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);

                        Logger.info("花费时间：" + (System.currentTimeMillis() - start));

                        while(true) {

                        }

                        //String title = activities.get(0).getTitle();
                        //Logger.info("缓存到内存场景活动 = " + title);

                        //Logger.info("缓存到内存根据prdCode获得title = " + titles.get(0));
                    }
                });
            }
        });
    }


}
