package com.sunlights.core.exceptions;


import com.sunlights.common.util.Message;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BusinessException extends RuntimeException {
	private String errorCode;
    private Message.Severity severity;
    private String detailMsg;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, String errorCode) {
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

    public BusinessException(Message.Severity severity, String errorCode, String message, String detailMsg){
        super(message);
        this.severity = severity;
        this.errorCode = errorCode;
        this.detailMsg = detailMsg;
        this.detailMsg = detailMsg;
    }

    public Message.Severity getSeverity(){
        return severity;
    }
    public String getDetailMsg(){
        return detailMsg;
    }
}
