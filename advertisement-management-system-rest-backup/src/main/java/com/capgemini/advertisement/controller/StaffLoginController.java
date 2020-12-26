package com.capgemini.advertisement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.entity.StaffLogin;
import com.capgemini.advertisement.exception.LoginException;
import com.capgemini.advertisement.exception.StaffException;
import com.capgemini.advertisement.service.StaffLoginService;
import com.capgemini.advertisement.service.StaffService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/api/staff",produces="application/json")
@Slf4j
public class StaffLoginController {
	@Autowired(required = false)
	private StaffLoginService staffLoginService;
	
	@Autowired(required = false)
	private StaffService staffService;
	
	@GetMapping("/login/{role}")
    public void isValidCredentails( @PathVariable String role){
		String staffRole=role;
        
		if(staffRole==null || !(staffRole.equals("ADMIN"))) {
			log.info("invalid");
			//return new ResponseEntity<>(HttpStatus.OK);
		}
        else {
        	log.info("login successful");
        	//return "Successful";
        }
		//return "null";
		
		
        
   
}
}
