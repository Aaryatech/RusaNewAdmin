package com.ats.rusaadmin.model;
 

public class LoginResponse {
	
	private boolean isError;
	private String msg;
	private User user;
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "LoginResponse [isError=" + isError + ", msg=" + msg + ", user=" + user + "]";
	}
	
	

}
