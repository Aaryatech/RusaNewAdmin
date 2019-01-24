package com.ats.rusaadmin.model;
 

public class Section {
	 
	private int sectionId; 
	private String sectionName; 
	private String sectionNo; 
	private String sectionDesc; 
	private String sectionAddDate; 
	private String sectionEditDate; 
	private String sectionSortNo; 
	private String sectionRemark; 
	private String sectionDateTime; 
	private int userId; 
	private int isActive; 
	private int delStatus;
	
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	public String getSectionDesc() {
		return sectionDesc;
	}
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}
	public String getSectionAddDate() {
		return sectionAddDate;
	}
	public void setSectionAddDate(String sectionAddDate) {
		this.sectionAddDate = sectionAddDate;
	}
	public String getSectionEditDate() {
		return sectionEditDate;
	}
	public void setSectionEditDate(String sectionEditDate) {
		this.sectionEditDate = sectionEditDate;
	}
	public String getSectionSortNo() {
		return sectionSortNo;
	}
	public void setSectionSortNo(String sectionSortNo) {
		this.sectionSortNo = sectionSortNo;
	}
	public String getSectionRemark() {
		return sectionRemark;
	}
	public void setSectionRemark(String sectionRemark) {
		this.sectionRemark = sectionRemark;
	}
	public String getSectionDateTime() {
		return sectionDateTime;
	}
	public void setSectionDateTime(String sectionDateTime) {
		this.sectionDateTime = sectionDateTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "Section [sectionId=" + sectionId + ", sectionName=" + sectionName + ", sectionNo=" + sectionNo
				+ ", sectionDesc=" + sectionDesc + ", sectionAddDate=" + sectionAddDate + ", sectionEditDate="
				+ sectionEditDate + ", sectionSortNo=" + sectionSortNo + ", sectionRemark=" + sectionRemark
				+ ", sectionDateTime=" + sectionDateTime + ", userId=" + userId + ", isActive=" + isActive
				+ ", delStatus=" + delStatus + "]";
	}
	
	

}
