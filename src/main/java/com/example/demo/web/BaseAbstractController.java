package com.example.demo.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.utils.exception.BaseException;
import com.example.demo.utils.response.ErrorResponse;
import com.example.demo.utils.response.ResponseFactory;


public abstract class BaseAbstractController{
    
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(BaseException ex) {
            return ResponseFactory.buildErrorResponse(ex);
    }  
    
    
    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }    
}
