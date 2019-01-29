package com.ats.rusaadmin.model;
 

public class Languages {
	 
	private int languagesId; 
	private String name; 
	private String code; 
	private String image; 
	private String directory; 
	private int sortOrder; 
	private String languagesStatus; 
	private String locale1; 
	private int defaultStatus; 
	private int isActive;
	public int getLanguagesId() {
		return languagesId;
	}
	public void setLanguagesId(int languagesId) {
		this.languagesId = languagesId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getLanguagesStatus() {
		return languagesStatus;
	}
	public void setLanguagesStatus(String languagesStatus) {
		this.languagesStatus = languagesStatus;
	}
	public String getLocale1() {
		return locale1;
	}
	public void setLocale1(String locale1) {
		this.locale1 = locale1;
	}
	public int getDefaultStatus() {
		return defaultStatus;
	}
	public void setDefaultStatus(int defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Languages [languagesId=" + languagesId + ", name=" + name + ", code=" + code + ", image=" + image
				+ ", directory=" + directory + ", sortOrder=" + sortOrder + ", languagesStatus=" + languagesStatus
				+ ", locale1=" + locale1 + ", defaultStatus=" + defaultStatus + ", isActive=" + isActive + "]";
	}
	
	

}
