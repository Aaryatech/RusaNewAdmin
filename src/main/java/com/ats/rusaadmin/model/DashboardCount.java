package com.ats.rusaadmin.model;
 

public class DashboardCount {
	 
	private int contactCount; 
	private int newUserCount; 
	private int collageUserCount; 
	private int univercityUserCount; 
	private int individualUserCount;
	public int getContactCount() {
		return contactCount;
	}
	public void setContactCount(int contactCount) {
		this.contactCount = contactCount;
	}
	public int getNewUserCount() {
		return newUserCount;
	}
	public void setNewUserCount(int newUserCount) {
		this.newUserCount = newUserCount;
	}
	public int getCollageUserCount() {
		return collageUserCount;
	}
	public void setCollageUserCount(int collageUserCount) {
		this.collageUserCount = collageUserCount;
	}
	public int getUnivercityUserCount() {
		return univercityUserCount;
	}
	public void setUnivercityUserCount(int univercityUserCount) {
		this.univercityUserCount = univercityUserCount;
	}
	public int getIndividualUserCount() {
		return individualUserCount;
	}
	public void setIndividualUserCount(int individualUserCount) {
		this.individualUserCount = individualUserCount;
	}
	@Override
	public String toString() {
		return "DashboardCount [contactCount=" + contactCount + ", newUserCount=" + newUserCount + ", collageUserCount="
				+ collageUserCount + ", univercityUserCount=" + univercityUserCount + ", individualUserCount="
				+ individualUserCount + "]";
	}
	
	

}
