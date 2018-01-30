package com.example.demo.utils.exception;

public enum Error {
    DATABASE(1000,"error.database" )    
    ;
    
    private final int code;
    private final String key;

    private Error(int code, String key) {
        this.code = code;
        this.key = key;
    }

    public String getKey() {
        return this.key;
        
    }
    
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + key;
    }
}