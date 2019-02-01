package com.ats.rusaadmin.model;

import java.util.List;
 

public class FreqAskQue {
	 
	private int faqId; 
	private int catId; 
	private int pageId;  
	private int sectionId; 
	private int faqSortNo; 
	private String faqRemark; 
	private String addDate; 
	private String editDate; 
	private int addedByUserId; 
	private int editByUserId; 
	private int isActive; 
	private int delStatus; 
	List<FreqAskQueDescription> descriptionList;
	
	public int getFaqId() {
		return faqId;
	}
	public void setFaqId(int faqId) {
		this.faqId = faqId;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getFaqSortNo() {
		return faqSortNo;
	}
	public void setFaqSortNo(int faqSortNo) {
		this.faqSortNo = faqSortNo;
	}
	public String getFaqRemark() {
		return faqRemark;
	}
	public void setFaqRemark(String faqRemark) {
		this.faqRemark = faqRemark;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
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
	public List<FreqAskQueDescription> getDescriptionList() {
		return descriptionList;
	}
	public void setDescriptionList(List<FreqAskQueDescription> descriptionList) {
		this.descriptionList = descriptionList;
	}
	@Override
	public String toString() {
		return "FreqAskQue [faqId=" + faqId + ", catId=" + catId + ", pageId=" + pageId + ", sectionId=" + sectionId
				+ ", faqSortNo=" + faqSortNo + ", faqRemark=" + faqRemark + ", addDate=" + addDate + ", editDate="
				+ editDate + ", addedByUserId=" + addedByUserId + ", editByUserId=" + editByUserId + ", isActive="
				+ isActive + ", delStatus=" + delStatus + ", descriptionList=" + descriptionList + "]";
	}
	
	

}
