package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService{

	@Autowired
	EmployeeRepository employeRepository;
	
	@Override
	public Employee save(Employee employee) {		
		return employeRepository.save(employee);
	}

	@Override
	public Employee update(Employee request, Employee old) {
		old.setToUpdate(request);
		return employeRepository.save(old);
	}

	@Override
	public Employee findById(Long id) {		
		return employeRepository.findById(id);
	}

	@Override
	public Employee findByName(String name) {
		return employeRepository.findByName(name);
	}

	@Override
	public List<Employee> findAll() {		
		return employeRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		employeRepository.delete(id);		
	}
	
	 public List<Employee> findByAssignedAndRol(boolean asigned, String rol){
		 return employeRepository.findByAssignedAndRol(asigned, rol);
	 }

	 public List<Employee> findByAssigned(boolean asigned){
		 return employeRepository.findByAssigned(asigned);
	 }
}
