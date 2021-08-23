package com.rajesh.demo22.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajesh.demo22.model.DeptEntity;
 
@Repository
public interface DeptRepository
        extends JpaRepository<DeptEntity, Long> {
 
}
