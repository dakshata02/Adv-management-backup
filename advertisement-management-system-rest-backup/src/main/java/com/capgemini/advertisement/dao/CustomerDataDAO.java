package com.capgemini.advertisement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.advertisement.entity.CustomerMaster;

@Repository
public interface CustomerDataDAO
				extends JpaRepository<CustomerMaster,Integer>{
	
}

