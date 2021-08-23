package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;
 
@Service
public class EmployeeService {
     
    @Autowired
    EmployeeRepository repository;
    
    @Autowired
	RestTemplate restTemplate;
     
    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }
     
    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
    
    // Rest call Impl
    public String getDeptDetailsbyId(Long id) throws RecordNotFoundException
    {
    	System.out.println("Getting  details for " + id);
		String response = restTemplate
				.exchange("http://localhost:7072/Depts/{id}"
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}, id).getBody();

		System.out.println("Response Received as " + response + " -  " + new Date());

		return response;
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
     
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(entity.getId());
         
        if(employee.isPresent())
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}