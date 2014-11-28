package com.sunlights.common.dal.impl;

import com.sunlights.common.vo.PageVo;
import org.junit.Test;
import play.libs.Json;

import static org.fest.assertions.Assertions.assertThat;

public class PageDaoImplTest {

    @Test
    public void pagerTest() {
        PageVo pageVo = new PageVo();
        pageVo.setPageSize(10);
        pageVo.setIndex(5);
        System.out.println(Json.toJson(pageVo));
        pageVo.setIndex(0);
        pageVo.setPageNum(1);
        System.out.println(Json.toJson(pageVo));
        assertThat(pageVo).toString();
    }

}