package com.example.demo.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "incoming_call")
public class IncomingCall implements Serializable {

	private static final long serialVersionUID = 1L;

	public IncomingCall() {

	}

	public IncomingCall(IncomingCall request) {		
		this.name = request.getName();
	}

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Size(max = 100)
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private Long phone;
	
	@Column(name = "iscallbackcall")
	private boolean isCallbackcall;
	
	@Size(max = 20)
	@Column(name = "callstate")
	private String callSate;
	
	@Column(name = "expectedtime")
	private int expectedTime;
	
	@Column(name = "initialprocessedtime")
    private ZonedDateTime initialProcessedTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public boolean isIscallbackcall() {
		return isCallbackcall;
	}

	public void setIscallbackcall(boolean isCallbackcall) {
		this.isCallbackcall = isCallbackcall;
	}

	public String getCallSate() {
		return callSate;
	}

	public void setCallSate(String callSate) {
		this.callSate = callSate;
	}	

	public int getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(int expectedTime) {
		this.expectedTime = expectedTime;
	}

	public ZonedDateTime getInitialProcessedTime() {
		return initialProcessedTime;
	}

	public void setInitialProcessedTime(ZonedDateTime initialProcessedTime) {
		this.initialProcessedTime = initialProcessedTime;
	}

	public void setToUpdate(IncomingCall incomingCall) {		
		this.id = incomingCall.getId();
		this.name = incomingCall.getName();
		this.phone = incomingCall.getPhone();
		this.isCallbackcall = incomingCall.isIscallbackcall();
		this.callSate = incomingCall.getCallSate();
	}		

}
