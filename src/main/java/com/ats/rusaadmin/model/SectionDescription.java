package com.ats.rusaadmin.model;
 
public class SectionDescription {
	 
	private int secDescId; 
	private int sectionId; 
	private int languageId; 
	private String sectionName; 
	private String sectionDesc; 
	private String exText;
	public int getSecDescId() {
		return secDescId;
	}
	public void setSecDescId(int secDescId) {
		this.secDescId = secDescId;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionDesc() {
		return sectionDesc;
	}
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}
	public String getExText() {
		return exText;
	}
	public void setExText(String exText) {
		this.exText = exText;
	}
	@Override
	public String toString() {
		return "SectionDescription [secDescId=" + secDescId + ", sectionId=" + sectionId + ", languageId=" + languageId
				+ ", sectionName=" + sectionName + ", sectionDesc=" + sectionDesc + ", exText=" + exText + "]";
	}
	
	

}
