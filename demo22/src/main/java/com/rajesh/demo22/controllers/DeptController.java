package com.rajesh.demo22.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.demo22.exception.RecordNotFoundException;
import com.rajesh.demo22.model.DeptEntity;
import com.rajesh.demo22.service.DeptService;
 
@RestController
@RequestMapping("/Depts")
public class DeptController
{
    @Autowired
    DeptService service;
 
    @GetMapping
    public ResponseEntity<List<DeptEntity>> getAllDept() {
        List<DeptEntity> list = service.getAllDeptEmployees();
 
        return new ResponseEntity<List<DeptEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<DeptEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        DeptEntity entity = service.getDeptById(id);
 
        return new ResponseEntity<DeptEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
  
}