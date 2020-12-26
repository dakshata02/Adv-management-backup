package com.capgemini.advertisement.service;

import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.LoginException;

public interface StaffLoginService {
	public boolean isValidCredentials(String email,String pass,String role) throws LoginException;

}
