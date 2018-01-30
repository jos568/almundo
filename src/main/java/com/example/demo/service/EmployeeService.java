package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Employee;

public interface EmployeeService {
	
	Employee save(Employee employee);
	
	Employee update(Employee request, Employee old);

	Employee findById(Long id);

    Employee findByName(String name);
    
    public List<Employee> findAll();
    
    public List<Employee>findByAssignedAndRol(boolean asigned, String rol);
    
    public List<Employee> findByAssigned(boolean asigned);
   
    public void delete(Long id);

}
