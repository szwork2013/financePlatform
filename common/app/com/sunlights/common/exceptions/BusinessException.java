package com.sunlights.common.exceptions;

import com.sunlights.common.vo.Message;

/**
 * Created by Administrator on 2014/11/1.
 */
public class BusinessException extends Exception {
   private Message message;

    public BusinessException(Throwable cause, Message message) {
        super(cause);
        this.message = message;
    }

    public Message getErrorMessage(){
        return this.message;
    }

    @Override
    public String getMessage() {
        return message.getSummary();
    }
}
