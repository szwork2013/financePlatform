package com.sunlights.common.vo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by Yuan on 2015/1/4.
 */
public class IntegerNullSerialize extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(integer == null ? 0 : integer.intValue());
    }
}
