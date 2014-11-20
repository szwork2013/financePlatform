package com.sunlights.common.dal;

import com.google.common.collect.Lists;
import models.Fund;
import org.junit.Assert;
import org.junit.Test;
import play.db.jpa.JPA;
import play.libs.F;

import java.util.List;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class EntityBaseDaoTest {

    @Test
    public void testFindBy() throws Exception {

        running(fakeApplication(), new Runnable() {
            public void run() {

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        EntityBaseDao entityBaseDao = new EntityBaseDao();
                        entityBaseDao.findBy(Fund.class, "fundCode", "000010");

                    }
                });
            }
        });

    }
}