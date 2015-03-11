package com.sunlights.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Http;

import static play.mvc.Controller.request;

/**
 * Created by Yuan on 2015/3/10.
 */
public class RequestUtil {

	public static <A> A getHeaderValue (String paramName, Class<A> aClass) {
		Http.Request request = request();
		String paramValue = request.getHeader(paramName);
		if(StringUtils.isBlank(paramValue)){
			throw new BusinessRuntimeException("Cannot find [" + paramName + "] from request header.");
		}

		return Json.fromJson(Json.parse(paramValue), aClass);
	}

	public static <A> A fromQueryString (Map<String, String[]> stringMap, Class<A> aClass) {
		Map<String, Object> map = new HashMap<>();
		for (String key : stringMap.keySet()) {
			String[] strings = stringMap.get(key);
			if (strings.length > 0) {
				map.put(key, strings[0]);
			}
		}
		Map<String, Object> recursive = recursive(map);
		JsonNode jsonNode = Json.toJson(recursive);
		A a = Json.fromJson(jsonNode, aClass);
		return a;
	}

	private static Map<String, Object> recursive (Map<String, Object> map) {
		Map value = new HashMap();
		for (String key : map.keySet()) {
			Object o = map.get(key);
			try {
				JsonNode parse = Json.parse((String) o);
				Map<String, Object> propertyMap = Json.fromJson(parse, Map.class);
				value.put(key, propertyMap);
				recursive(propertyMap);
			} catch (Exception e) {
				value.put(key, o);
			}
		}
		return value;
	}

}
