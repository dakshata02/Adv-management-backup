package com.capgemini.advertisement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.entity.StaffLogin;

public interface StaffLoginDao extends JpaRepository<StaffLogin, String> {
	Staff findByEmail(String email);
	Staff findByPass(String pass);
	Staff findByRole(String role);

}
