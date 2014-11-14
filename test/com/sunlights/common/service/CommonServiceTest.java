package com.sunlights.common.service;

import com.sunlights.common.vo.DictVo;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.Json;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class CommonServiceTest {

    @Test
    public void testFindValueByCatPointKey() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                JPA.withTransaction(new play.libs.F.Callback0() {
                    public void invoke() {
                        CommonService commonService = new CommonService();
                        String value = commonService.findValueByCatPointKey("FP.RECOMMEND.TYPE");
                        Logger.info("[value]" + value);
                        assertThat(value).isEqualTo("推荐");
                    }
                });
            }

        });
    }

    @Test
    public void testFindDictsByCat() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {
                JPA.withTransaction(new play.libs.F.Callback0() {
                    public void invoke() {
                        CommonService commonService = new CommonService();
                        List<DictVo> dicts = commonService.findDictsByCat("FP.RECOMMEND.TYPE");
                        Logger.info("[dicts]" + Json.toJson(dicts));
                        assertThat(dicts).isNotEmpty();
                    }
                });
            }

        });

    }

    @Test
    public void testFindDictMapByCat() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                JPA.withTransaction(new play.libs.F.Callback0() {
                    public void invoke() {
                        CommonService commonService = new CommonService();
                        Map<String, String> dictMap = commonService.findDictMapByCat("FP.RECOMMEND.TYPE");
                        Logger.info("[dictMap]" + dictMap);
                        assertThat(dictMap).isNotEmpty();
                    }
                });
            }

        });
    }

    @Test
    public void testFindDictByCatPointKey() throws Exception {

    }
}