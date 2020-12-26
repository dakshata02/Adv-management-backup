package com.capgemini.advertisement.entity;
public class StaffLogin {
   
   // @NotNull(message="email must not be empty")
    private Integer staffId;
   // @NotEmpty(message="Password must not be empty")
    private String password;
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
   
   
}
 