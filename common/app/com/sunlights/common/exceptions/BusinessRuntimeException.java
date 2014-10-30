package com.sunlights.common.exceptions;


import com.sunlights.common.utils.msg.Message;

import java.io.PrintWriter;
import java.io.StringWriter;


public class BusinessRuntimeException extends RuntimeException {
	private String errorCode;
    private Message.Severity severity;
    private String detailMsg;

	public BusinessRuntimeException() {
		super();
	}

	public BusinessRuntimeException(String message) {
		super(message);
	}

	public BusinessRuntimeException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessRuntimeException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getDetailMessage() {
		Throwable cause = this.getCause();
		if (cause == null) {
			cause = this;
		}
		StringWriter stringWriter = new StringWriter();
		cause.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

    public BusinessRuntimeException(Message message){
        super(message.getSummary());
        this.severity = message.getSeverity();
        this.errorCode = message.getCode();
        this.detailMsg = message.getDetail();
    }

    public BusinessRuntimeException(Message.Severity severity, String code, String summary, String detail){
        super(summary);
        this.errorCode = code;
        this.severity = severity;
        this.detailMsg = detail;
    }

    public Message.Severity getSeverity(){
        return severity;
    }
    public String getDetailMsg(){
        return detailMsg;
    }
}
