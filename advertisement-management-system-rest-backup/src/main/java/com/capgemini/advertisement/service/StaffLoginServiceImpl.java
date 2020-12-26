package com.capgemini.advertisement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.advertisement.dao.StaffLoginDao;
import com.capgemini.advertisement.dao.StaffSpringDataDAO;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.LoginException;

public class StaffLoginServiceImpl implements StaffLoginService{
	@Autowired
	private StaffLoginDao staffLoginDao;

	@Override
	public boolean isValidCredentials(String email,String pass,String role) throws LoginException {
		Staff staff=new Staff();
		Staff emailID=staffLoginDao.findByEmail(staff.getEmail());
		if(emailID==null) {
			throw new LoginException("User not Found");
		}
		Staff pwd=staffLoginDao.findByPass(staff.getPassword());
		if(!pwd.getPassword().equals(staff.getPassword())) {
			throw new LoginException("password not match");
		}
		return true;
				
		
		
		
	
//		if(staff.getRole().equals("ADMIN")) {
//			List<Staff> s=staffLoginDao.findByEmailAndPassword(email, pass);
//			if(s.contains(email) && s.contains(pass)) {
//				return true;
//			}
//			else {
//				throw new LoginException("Invalid Credentials");
//			}
//			
//		}
//		else {
//			return false;
//		}
		
			
	}

}
