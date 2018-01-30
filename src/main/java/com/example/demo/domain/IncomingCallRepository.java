package com.example.demo.domain;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;

public interface IncomingCallRepository extends BaseRepository<IncomingCall, Long>{
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    IncomingCall findById(Long id);

    IncomingCall findByName(String name);
    
    public List<IncomingCall> findAll();
    
    public List<IncomingCall> findByCallSate(String state);
   
    public void delete(Long id);

	
}
