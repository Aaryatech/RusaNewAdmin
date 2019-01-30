package com.ats.rusaadmin.model;
 

public class SubCategoryList {
 
	private int subCatId; 
	private String subCatName; 
	private String subCatDesc; 
	private String subSlugName; 
	private int pageId; 
	private int subSortNo;
	public int getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getSubCatDesc() {
		return subCatDesc;
	}
	public void setSubCatDesc(String subCatDesc) {
		this.subCatDesc = subCatDesc;
	}
	public String getSubSlugName() {
		return subSlugName;
	}
	public void setSubSlugName(String subSlugName) {
		this.subSlugName = subSlugName;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getSubSortNo() {
		return subSortNo;
	}
	public void setSubSortNo(int subSortNo) {
		this.subSortNo = subSortNo;
	}
	@Override
	public String toString() {
		return "SubCategoryList [subCatId=" + subCatId + ", subCatName=" + subCatName + ", subCatDesc=" + subCatDesc
				+ ", subSlugName=" + subSlugName + ", pageId=" + pageId + ", subSortNo=" + subSortNo + "]";
	}
	
	
	
}
