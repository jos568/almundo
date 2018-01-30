/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utils.response;



import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.stereotype.Component;

import com.example.demo.utils.exception.BaseException;



@Component
public class ResponseFactory {
 
    
    @Autowired
    MessageSourceService messageSourceService;
    
    
    public static ResponseEntity<ErrorResponse> buildErrorResponse(BaseException ex)
    {
        return buildErrorResponse(ex,HttpStatus.PRECONDITION_FAILED);
    }

    public static ResponseEntity<ResponseAbstract> buildResponse(Object o)
    {
        ResponseAbstract response;
        if (o instanceof List)
        {
            response = new ResponseListOfObjects((List<Object>)o);
        }
        else
        {
            response = new ResponseSingleObject(o);
        }

		return new ResponseEntity<ResponseAbstract>(response, HttpStatus.OK);
    }

    
    public static ResponseEntity<ErrorResponse> buildErrorResponse(BaseException ex, HttpStatus httpCode)
    {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(),ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse,httpCode);
    }
	
	
    public  ResponseEntity<ErrorResponse> buildErrorResponse(com.example.demo.utils.exception.Error error, HttpStatus httpCode)
    {
        ErrorResponse errorResponse = new ErrorResponse(error.getCode() , messageSourceService.getMessage(error.getKey()) );        
        return new ResponseEntity<ErrorResponse>(errorResponse,httpCode);
    }

}
