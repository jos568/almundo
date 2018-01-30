package com.example.demo.utils.exception;


public class BaseException extends RuntimeException {
    private com.example.demo.utils.exception.Error error;  
    public BaseException(com.example.demo.utils.exception.Error error, String message){           
        super(message);
        this.error = error;        
    }        
    public int getCode()
    {
        return this.error.getCode();
    }

}
