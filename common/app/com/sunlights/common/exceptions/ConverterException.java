package com.sunlights.common.exceptions;

/**
 * Created by Administrator on 2014/11/13.
 */
public class ConverterException extends Exception {

    public ConverterException() {
        super();
    }

    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConverterException(Throwable cause) {
        super(cause);
    }
}
