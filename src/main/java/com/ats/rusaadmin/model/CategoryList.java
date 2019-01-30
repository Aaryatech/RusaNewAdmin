package com.ats.rusaadmin.model;

import java.util.List;
 

public class CategoryList {
 
	private int catId; 
	private String catName; 
	private String catDesc; 
	private String slugName; 
	private int pageId; 
	private int catSortNo; 
	List<SubCategoryList> subCatList;
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	public String getSlugName() {
		return slugName;
	}
	public void setSlugName(String slugName) {
		this.slugName = slugName;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getCatSortNo() {
		return catSortNo;
	}
	public void setCatSortNo(int catSortNo) {
		this.catSortNo = catSortNo;
	}
	public List<SubCategoryList> getSubCatList() {
		return subCatList;
	}
	public void setSubCatList(List<SubCategoryList> subCatList) {
		this.subCatList = subCatList;
	}
	@Override
	public String toString() {
		return "CategoryList [catId=" + catId + ", catName=" + catName + ", catDesc=" + catDesc + ", slugName="
				+ slugName + ", pageId=" + pageId + ", catSortNo=" + catSortNo + ", subCatList=" + subCatList + "]";
	}
	
}
