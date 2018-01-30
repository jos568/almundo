package com.example.demo.utils.response;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public abstract class ResponseAbstract {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date messageGenerationTime;
    private Object data;
    private boolean success;
    private String okMessage;

    public ResponseAbstract(boolean success, Object o) {
        this.messageGenerationTime = new Date();
        this.data = o;
        this.success = success;
        this.okMessage = okMessage;
    }

    public ResponseAbstract(boolean success, Object o, String okMessage) {
        this.messageGenerationTime = new Date();
        this.data = o;
        this.success = success;
    }
    
    public Date getMessageGenerationTime() {
        return this.messageGenerationTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOkMessage() {
        return okMessage;
    }

    public void setOkMessage(String okMessage) {
        this.okMessage = okMessage;
    }
    
}