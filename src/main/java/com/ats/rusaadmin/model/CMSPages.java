package com.ats.rusaadmin.model;

import java.util.List;
  

public class CMSPages {
	 
	private int cmsPageId; 
	private int pageId; 
	private int parentMenu; 
	private int pageOrder; 
	private String featuredImage; 
	private String featuredImageAlignment; 
	private String downloadPdf; 
	private int isActive; 
	private int delStatus; 
	private String addDate; 
	private String editDate; 
	private int addedByUserId; 
	private int editByUserId; 
	List<CMSPageDescription> detailList;
	
	public int getCmsPageId() {
		return cmsPageId;
	}
	public void setCmsPageId(int cmsPageId) {
		this.cmsPageId = cmsPageId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(int parentMenu) {
		this.parentMenu = parentMenu;
	}
	public int getPageOrder() {
		return pageOrder;
	}
	public void setPageOrder(int pageOrder) {
		this.pageOrder = pageOrder;
	}
	public String getFeaturedImage() {
		return featuredImage;
	}
	public void setFeaturedImage(String featuredImage) {
		this.featuredImage = featuredImage;
	}
	public String getFeaturedImageAlignment() {
		return featuredImageAlignment;
	}
	public void setFeaturedImageAlignment(String featuredImageAlignment) {
		this.featuredImageAlignment = featuredImageAlignment;
	}
	public String getDownloadPdf() {
		return downloadPdf;
	}
	public void setDownloadPdf(String downloadPdf) {
		this.downloadPdf = downloadPdf;
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
	public List<CMSPageDescription> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<CMSPageDescription> detailList) {
		this.detailList = detailList;
	}
	@Override
	public String toString() {
		return "CMSPages [cmsPageId=" + cmsPageId + ", pageId=" + pageId + ", parentMenu=" + parentMenu + ", pageOrder="
				+ pageOrder + ", featuredImage=" + featuredImage + ", featuredImageAlignment=" + featuredImageAlignment
				+ ", downloadPdf=" + downloadPdf + ", isActive=" + isActive + ", delStatus=" + delStatus + ", addDate="
				+ addDate + ", editDate=" + editDate + ", addedByUserId=" + addedByUserId + ", editByUserId="
				+ editByUserId + ", detailList=" + detailList + "]";
	}
	
	

}
