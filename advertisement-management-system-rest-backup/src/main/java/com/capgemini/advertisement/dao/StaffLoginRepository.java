package com.capgemini.advertisement.dao;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.advertisement.entity.Staff;


/*
 * 
 * @author 
 * 
 * */
@Repository
public interface StaffLoginRepository extends JpaRepository<Staff, Integer> {


}