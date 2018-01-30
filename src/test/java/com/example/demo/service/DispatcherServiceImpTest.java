package com.example.demo.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;
import java.util.List;

import org.aspectj.weaver.ast.Call;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.domain.Employee;
import com.example.demo.domain.IncomingCall;
import com.example.demo.utils.enums.CallState;
import com.example.demo.utils.enums.EmployeeRol;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@TestPropertySource("application-test.properties")
public class DispatcherServiceImpTest  {
	
	@Autowired
	IncomingCallService incomingCallService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DispatcherService dispatcherService;

	@Before
    public void createCalls() {
		int i = 0;
		int maxTime = 5;
		int minTime = 2;
		boolean isCallback = true;
		while (i < 30) {
			i++;
			IncomingCall call = new IncomingCall();
			call.setCallSate(CallState.waiting.toString());
			call.setExpectedTime(minTime);
			call.setInitialProcessedTime(ZonedDateTime.now());
			call.setIscallbackcall(isCallback);
			call.setName("Incoming Call number " + i);
			call.setPhone(111232114L);
			if(isCallback) {
				isCallback = !isCallback;				
			}
			if(minTime == maxTime) {
				minTime = 2;
			}else {
				minTime++;
			}	
			incomingCallService.save(call);
		}
		i=0;
		while (i < 5) {
			i++;
			Employee employee = new Employee();
			employee.setName("employee test " +i);
			employee.setAssigned(false);			
			employee.setRol(EmployeeRol.operator.toString());
			employee.setAssignedCallId(0L);
			if(isCallback) {
				isCallback = !isCallback;				
			}			
			employeeService.save(employee);
		}
		i=0;
		while (i < 3) {
			i++;
			Employee employee = new Employee();
			employee.setName("director test " +i);
			employee.setAssigned(false);			
			employee.setRol(EmployeeRol.director.toString());
			employee.setAssignedCallId(0L);
			if(isCallback) {
				isCallback = !isCallback;				
			}			
			employeeService.save(employee);
		}
		i=0;
		while (i < 2) {
			i++;
			Employee employee = new Employee();
			employee.setName("supervisor test " +i);
			employee.setAssigned(false);			
			employee.setRol(EmployeeRol.supervisor.toString());
			employee.setAssignedCallId(0L);
			if(isCallback) {
				isCallback = !isCallback;				
			}			
			employeeService.save(employee);
		}
	}
	
	@Test
	public void dispatcherTest() {
		dispatcherService.dispatcher();
		List<IncomingCall> incomingCalls = incomingCallService.findByCallSate(CallState.waiting.toString());		
		assertTrue(30 > incomingCalls.size());		
	}
	
}
