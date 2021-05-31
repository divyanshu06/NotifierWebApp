package com.notifier.model;
import java.io.Serializable;

public class UserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private long mobileNumber;
	private String email;
	private String password;
	
	public UserBean() {}
	
	public UserBean(String username, long mobileNumber, String email, String password) {
		this.username = username;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
