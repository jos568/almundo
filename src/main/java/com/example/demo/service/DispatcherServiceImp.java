package com.example.demo.service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Employee;
import com.example.demo.domain.IncomingCall;
import com.example.demo.utils.enums.CallState;
import com.example.demo.utils.enums.EmployeeRol;

@Service
@Transactional
public class DispatcherServiceImp implements DispatcherService {

	@Autowired
	IncomingCallService incomingCallService;

	@Autowired
	EmployeeService employeeService;

	private static final Logger LOG = LoggerFactory.getLogger(DispatcherServiceImp.class);

	@Override
	public void dispatcher() {
		try {
			List<IncomingCall> unassignedCalls = getUnassignedCalls(CallState.waiting.toString());
			if (unassignedCalls != null && unassignedCalls.size() > 0) {
				for (int i = 0; i < unassignedCalls.size(); i++) {
					List<Employee> unassignedEmployee = getUnassignedEmployee(false);
					if (unassignedEmployee != null && unassignedEmployee.size() > 0) {
						assignCallState(unassignedCalls.get(i), unassignedEmployee.get(0));
						LOG.info("Call is assigned to employee " + unassignedEmployee.get(0).getName());
					} else {
						if (unassignedCalls.get(i).isIscallbackcall()) {
							LOG.info("Please wait for our call");
						} else {
							LOG.info("Your position in the queue is " + i);
						}
						List<Employee> assignedEmployee = getAssignedEmployees();
						if (assignedEmployee != null && assignedEmployee.size() > 0) {
							for (int o = 0; o < assignedEmployee.size(); o++) {
								IncomingCall inAtenttion = getCall(assignedEmployee.get(o).getAssignedCallId());
								if(inAtenttion != null) {
									if (compareTime(inAtenttion.getInitialProcessedTime(), inAtenttion.getExpectedTime())) {
										unAssignCallState(inAtenttion, assignedEmployee.get(o));
									}
								}								
							}
						}
					}

				}
			} else {
				try {
					List<Employee> assignedEmployee = getAssignedEmployees();
					if (assignedEmployee != null && assignedEmployee.size() > 0) {
						for (int o = 0; o < assignedEmployee.size(); o++) {
							IncomingCall inAtenttion = getCall(assignedEmployee.get(o).getAssignedCallId());
							if(inAtenttion != null) {
								if (compareTime(inAtenttion.getInitialProcessedTime(), inAtenttion.getExpectedTime())) {
									unAssignCallState(inAtenttion, assignedEmployee.get(o));
								}
							}								
						}
					}
				}catch(Exception ex){
					
				}				
				LOG.info("No calls in the queue ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private List<IncomingCall> getUnassignedCalls(String state) {
		return incomingCallService.findByCallSate(state);
	}

	private IncomingCall getCall(long id) {
		return incomingCallService.findById(id);
	}
	
	private  List<Employee> getAssignedEmployees() {
		return employeeService.findByAssigned(true);
	}

	private List<Employee> getUnassignedEmployee(boolean isAssigned) {
		String rol = EmployeeRol.operator.toString();

		List<Employee> unassignedEmployee = employeeService.findByAssignedAndRol(isAssigned, rol);

		if (rol.equals(EmployeeRol.operator.toString()) && unassignedEmployee == null
				|| unassignedEmployee.size() <= 0) {
			rol = EmployeeRol.supervisor.toString();
			unassignedEmployee = employeeService.findByAssignedAndRol(isAssigned, rol);
		}

		if (rol.equals(EmployeeRol.supervisor.toString()) && unassignedEmployee == null
				|| unassignedEmployee.size() <= 0) {
			rol = EmployeeRol.director.toString();
			unassignedEmployee = employeeService.findByAssignedAndRol(isAssigned, rol);
		}

		return unassignedEmployee;
	}

	private void assignCallState(IncomingCall oldIncomingCall, Employee oldEmployee) {
		IncomingCall request = new IncomingCall();
		request.setToUpdate(oldIncomingCall);
		request.setCallSate(CallState.inAtenttion.toString());
		incomingCallService.update(request, oldIncomingCall);
		Employee requestEmployee = new Employee();
		requestEmployee.setToUpdate(oldEmployee);
		requestEmployee.setAssignedCallId(oldIncomingCall.getId());
		requestEmployee.setAssigned(true);
		employeeService.update(requestEmployee, oldEmployee);
	}

	private void unAssignCallState(IncomingCall oldIncomingCall, Employee oldEmployee) {
		IncomingCall request = new IncomingCall();
		request.setToUpdate(oldIncomingCall);
		request.setCallSate(CallState.finished.toString());
		incomingCallService.update(request, oldIncomingCall);
		Employee requestEmployee = new Employee();
		requestEmployee.setToUpdate(oldEmployee);
		requestEmployee.setAssignedCallId(0L);
		requestEmployee.setAssigned(false);
		employeeService.update(requestEmployee, oldEmployee);
	}

	private boolean compareTime(ZonedDateTime callInitalTime, int callDuration) {
		callInitalTime = ZonedDateTime.now();
		ZonedDateTime t2 = ZonedDateTime.now();
		callInitalTime = callInitalTime.minusSeconds(5L);
		Duration duration = Duration.between(callInitalTime, t2);
		if (duration.getSeconds() >= callDuration) {
			return true;
		}
		return false;
	}
	
	
}
