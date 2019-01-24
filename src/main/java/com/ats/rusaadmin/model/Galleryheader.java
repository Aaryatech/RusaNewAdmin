package com.ats.rusaadmin.model;

import java.util.List;
 

public class Galleryheader {
	 
	private int galleryHeaderId; 
	private String galleryTitle; 
	private String galleryAddDate; 
	private String galleryEditDate; 
	private int catId; 
	private int subCatId; 
	private String galleryHeaderDesc; 
	private String gallerySortNo; 
	private int userId; 
	private int isActive; 
	private int delStatus; 
	List<GalleryDetail> detailList;  
	private int exInt1; 
	private int exInt2; 
	private int exInt3; 
	private String exVar1; 
	private String exVar2; 
	private String exVar3; 
	private String exVar4; 
	private String exVar5; 
	private String exDate1; 
	private String exDate2; 
	private float exFloat1; 
	private float exFloat2; 
	private float exFloat3;
	public int getGalleryHeaderId() {
		return galleryHeaderId;
	}
	public void setGalleryHeaderId(int galleryHeaderId) {
		this.galleryHeaderId = galleryHeaderId;
	}
	public String getGalleryTitle() {
		return galleryTitle;
	}
	public void setGalleryTitle(String galleryTitle) {
		this.galleryTitle = galleryTitle;
	}
	public String getGalleryAddDate() {
		return galleryAddDate;
	}
	public void setGalleryAddDate(String galleryAddDate) {
		this.galleryAddDate = galleryAddDate;
	}
	public String getGalleryEditDate() {
		return galleryEditDate;
	}
	public void setGalleryEditDate(String galleryEditDate) {
		this.galleryEditDate = galleryEditDate;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}
	public String getGalleryHeaderDesc() {
		return galleryHeaderDesc;
	}
	public void setGalleryHeaderDesc(String galleryHeaderDesc) {
		this.galleryHeaderDesc = galleryHeaderDesc;
	}
	public String getGallerySortNo() {
		return gallerySortNo;
	}
	public void setGallerySortNo(String gallerySortNo) {
		this.gallerySortNo = gallerySortNo;
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
	public List<GalleryDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<GalleryDetail> detailList) {
		this.detailList = detailList;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public int getExInt2() {
		return exInt2;
	}
	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}
	public int getExInt3() {
		return exInt3;
	}
	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public String getExVar2() {
		return exVar2;
	}
	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}
	public String getExVar3() {
		return exVar3;
	}
	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}
	public String getExVar4() {
		return exVar4;
	}
	public void setExVar4(String exVar4) {
		this.exVar4 = exVar4;
	}
	public String getExVar5() {
		return exVar5;
	}
	public void setExVar5(String exVar5) {
		this.exVar5 = exVar5;
	}
	public String getExDate1() {
		return exDate1;
	}
	public void setExDate1(String exDate1) {
		this.exDate1 = exDate1;
	}
	public String getExDate2() {
		return exDate2;
	}
	public void setExDate2(String exDate2) {
		this.exDate2 = exDate2;
	}
	public float getExFloat1() {
		return exFloat1;
	}
	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}
	public float getExFloat2() {
		return exFloat2;
	}
	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
	}
	public float getExFloat3() {
		return exFloat3;
	}
	public void setExFloat3(float exFloat3) {
		this.exFloat3 = exFloat3;
	}
	@Override
	public String toString() {
		return "Galleryheader [galleryHeaderId=" + galleryHeaderId + ", galleryTitle=" + galleryTitle
				+ ", galleryAddDate=" + galleryAddDate + ", galleryEditDate=" + galleryEditDate + ", catId=" + catId
				+ ", subCatId=" + subCatId + ", galleryHeaderDesc=" + galleryHeaderDesc + ", gallerySortNo="
				+ gallerySortNo + ", userId=" + userId + ", isActive=" + isActive + ", delStatus=" + delStatus
				+ ", detailList=" + detailList + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", exVar4=" + exVar4
				+ ", exVar5=" + exVar5 + ", exDate1=" + exDate1 + ", exDate2=" + exDate2 + ", exFloat1=" + exFloat1
				+ ", exFloat2=" + exFloat2 + ", exFloat3=" + exFloat3 + "]";
	}
	
	

}
