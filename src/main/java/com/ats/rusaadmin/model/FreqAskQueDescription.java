package com.ats.rusaadmin.model;
 

public class FreqAskQueDescription {
	 
	private int faqDescId; 
	private int faqId; 
	private int languageId;  
	private int catId; 
	private int subCatId; 
	private String faqQue; 
	private String faqAns;
	
	public int getFaqDescId() {
		return faqDescId;
	}
	public void setFaqDescId(int faqDescId) {
		this.faqDescId = faqDescId;
	}
	public int getFaqId() {
		return faqId;
	}
	public void setFaqId(int faqId) {
		this.faqId = faqId;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
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
	public String getFaqQue() {
		return faqQue;
	}
	public void setFaqQue(String faqQue) {
		this.faqQue = faqQue;
	}
	public String getFaqAns() {
		return faqAns;
	}
	public void setFaqAns(String faqAns) {
		this.faqAns = faqAns;
	}
	@Override
	public String toString() {
		return "FreqAskQueDescription [faqDescId=" + faqDescId + ", faqId=" + faqId + ", languageId=" + languageId
				+ ", catId=" + catId + ", subCatId=" + subCatId + ", faqQue=" + faqQue + ", faqAns=" + faqAns + "]";
	}
	
	

}
