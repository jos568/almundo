package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.IncomingCall;
import com.example.demo.domain.IncomingCallRepository;


@Service
@Transactional

public class IncomingCallServiceImp implements IncomingCallService {
	
	@Autowired
	IncomingCallRepository incomingCallRepository;

	@Override
	public IncomingCall save(IncomingCall request) {		
		return incomingCallRepository.save(request);
	}

	@Override
	public IncomingCall update(IncomingCall request, IncomingCall oldIncomingCall) {
		oldIncomingCall.setToUpdate(request);
		return incomingCallRepository.save(oldIncomingCall);
	}

	@Override
	public IncomingCall findById(Long id) {		
		return incomingCallRepository.findById(id);
	}

	@Override
	public IncomingCall findByName(String name) {		
		return incomingCallRepository.findByName(name);
	}

	@Override
	public List<IncomingCall> findAll() {
		return incomingCallRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		incomingCallRepository.delete(id);
	}
	
	@Override
	public List<IncomingCall> findByCallSate(String state){
		return incomingCallRepository.findByCallSate(state);
	}

}
