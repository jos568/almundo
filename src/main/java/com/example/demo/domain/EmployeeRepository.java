package com.example.demo.domain;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;

public interface EmployeeRepository extends BaseRepository<Employee, Long>{
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Employee findById(Long id);

    Employee findByName(String name);
    
    public List<Employee> findAll();
    
    public List<Employee> findByAssignedAndRol(boolean asigned, String rol);
    
    public List<Employee> findByAssigned(boolean asigned);
   
    public void delete(Long id);

	
}
