package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.IncomingCall;

public interface IncomingCallService {
	
	IncomingCall save(IncomingCall request);
	
	IncomingCall update(IncomingCall request, IncomingCall oldIncomingCall);

	IncomingCall findById(Long id);

    IncomingCall findByName(String name);
    
    public List<IncomingCall> findAll();
    
    public List<IncomingCall> findByCallSate(String state);
   
    public void delete(Long id);

}
