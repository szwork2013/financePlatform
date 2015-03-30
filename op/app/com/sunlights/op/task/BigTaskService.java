package com.sunlights.op.task;


import com.sunlights.op.common.util.ClassUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guxuelong on 2014/12/12.
 */
public class BigTaskService {
    private static final int MAX_THREAD = 10;

    private static ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);

    private Class<?> cls;

    public BigTaskService(Class<?> cls) {
        this.cls = cls;
    }

    /**
     * 建立线程池(最大限制为10),并发执行多任务
     *
     * @throws Exception
     */
    public void doTask() throws Exception {
        List<Class<?>> list = ClassUtil.getAllAssignedClass(cls);
        for (Class<?> aClass : list) {
            executorService.execute((Runnable) aClass.newInstance());
        }
    }
}