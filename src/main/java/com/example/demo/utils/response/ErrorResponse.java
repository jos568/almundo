package com.example.demo.utils.response;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse extends ResponseAbstract {

    private int errorCode;
    private String message;
    private List<String> errors = new ArrayList<String>();

    /*
    public ErrorResponse()
    {
        super(false,null);
    }
     */
    public ErrorResponse(int errorCode, String message) {
        super(false, null);
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse(int errorCode, String message, List<String> errors) {
        super(false, null);
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}