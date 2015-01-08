package com.sunlights.common.vo;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by Yuan on 2015/1/4.
 */
public class IntegerNullSerialize extends JsonSerializer<Integer> {

	@Override
	public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeNumber(integer == null ? 0 : integer.intValue());
	}
}
