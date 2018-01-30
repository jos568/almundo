package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	public Employee() {

	}

	public Employee(Employee request) {
		this.name = request.getRol();
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
		
	@Column(name = "isassigned")
	private boolean assigned;

	@Size(max = 100)
	@Column(name = "rol")
	private String rol;
	
	@Column(name = "assignedCallId")
	private Long assignedCallId;

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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public Long getAssignedCallId() {
		return assignedCallId;
	}

	public void setAssignedCallId(Long assignedCallId) {
		this.assignedCallId = assignedCallId;
	}

	public void setToUpdate(Employee employee) {		
		this.id = employee.getId();
		this.name = employee.getName();
		this.assigned = employee.isAssigned();
		this.rol = employee.getRol();
		this.assignedCallId = employee.getAssignedCallId();
	}

}
