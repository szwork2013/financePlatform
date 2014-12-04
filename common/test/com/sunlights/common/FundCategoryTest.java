package com.sunlights.common;

import org.junit.Test;
import play.libs.Json;

import static org.fest.assertions.Assertions.assertThat;

public class FundCategoryTest {

    @Test
    public void testFindFundCategoryBy() throws Exception {
        FundCategory monetary = FundCategory.findFundCategoryBy("MONETARY");
        System.out.println("[monetary]" + Json.toJson(monetary));
        assertThat(monetary).isNotNull();
    }
}