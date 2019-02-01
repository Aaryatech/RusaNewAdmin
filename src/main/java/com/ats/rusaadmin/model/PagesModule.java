package com.ats.rusaadmin.model;
 

public class PagesModule {
 
	private int id; 
	private int pageId; 
	private int moduleId;
	private int primaryKeyId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getPrimaryKeyId() {
		return primaryKeyId;
	}
	public void setPrimaryKeyId(int primaryKeyId) {
		this.primaryKeyId = primaryKeyId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	@Override
	public String toString() {
		return "PagesModule [id=" + id + ", pageId=" + pageId + ", moduleId=" + moduleId + ", primaryKeyId="
				+ primaryKeyId + "]";
	}
	
	
	
}
