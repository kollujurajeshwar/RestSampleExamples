package com.rajesh.demo22.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajesh.demo22.exception.RecordNotFoundException;
import com.rajesh.demo22.model.DeptEntity;
import com.rajesh.demo22.repository.DeptRepository;

@Service
public class DeptService {

	@Autowired
	DeptRepository repository;

	public List<DeptEntity> getAllDeptEmployees() {
		List<DeptEntity> employeeList = repository.findAll();

		if (employeeList.size() > 0) {
			return employeeList;
		} else {
			return new ArrayList<DeptEntity>();
		}
	}

	public DeptEntity getDeptById(Long id) throws RecordNotFoundException {
		Optional<DeptEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No Dept record exist for given id");
		}
	}

}