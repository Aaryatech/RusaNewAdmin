package com.ats.rusaadmin.model;
 

public class User {
	
	 
	private int userId; 
	private String userName; 
	private String userPass; 
	private String userEmail; 
	private String firstname; 
	private String middlename; 
	private String lastname; 
	private int sortNo; 
	private int delStatus; 
	private int isActive; 
	private String roles; 
	private String lastloginDate; 
	private String createdDate; 
	private String modifiedDate; 
	private int loginFailureCount;  
	private int addedByUserId;
	private int editByUserId;
	
	
	public int getAddedByUserId() {
		return addedByUserId;
	}
	public void setAddedByUserId(int addedByUserId) {
		this.addedByUserId = addedByUserId;
	}
	public int getEditByUserId() {
		return editByUserId;
	}
	public void setEditByUserId(int editByUserId) {
		this.editByUserId = editByUserId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getLastloginDate() {
		return lastloginDate;
	}
	public void setLastloginDate(String lastloginDate) {
		this.lastloginDate = lastloginDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public int getLoginFailureCount() {
		return loginFailureCount;
	}
	public void setLoginFailureCount(int loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", userEmail="
				+ userEmail + ", firstname=" + firstname + ", middlename=" + middlename + ", lastname=" + lastname
				+ ", sortNo=" + sortNo + ", delStatus=" + delStatus + ", isActive=" + isActive + ", roles=" + roles
				+ ", lastloginDate=" + lastloginDate + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", loginFailureCount=" + loginFailureCount + ", addedByUserId=" + addedByUserId + ", editByUserId="
				+ editByUserId + "]";
	}
	 

	
}
