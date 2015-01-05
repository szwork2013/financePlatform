package com.sunlights.common.vo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Yuan on 2015/1/4.
 */
public class StringNullSerialize extends JsonSerializer<String> {

	private static String BLANK = "--";

	@Override
	public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		jsonGenerator.writeString(StringUtils.isEmpty(s) ? BLANK : s);
	}
}
