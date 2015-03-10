package com.sunlights.common.utils;

import static org.fest.assertions.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sunlights.common.vo.PageVo;

public class RequestUtilTest {

	@Test
	public void testFromQueryString() throws Exception {
		Map<String, String[]> stringMap = new HashMap<>();
		String[] pageSize = { "10" };
		stringMap.put("pageSize", pageSize);
		PageVo pageVo = RequestUtil.fromQueryString(stringMap, PageVo.class);
		assertThat(pageVo.getPageSize()).isEqualTo(10);
		String[] filter = { "{\"LIKES_companyName\":\"1111\",\"LIKES_companyCode\":\"222222\"}" };
		stringMap.put("filter", filter);
		pageVo = RequestUtil.fromQueryString(stringMap, PageVo.class);
		assertThat(pageVo.get("LIKES_companyName")).isEqualTo("1111");

	}

}
