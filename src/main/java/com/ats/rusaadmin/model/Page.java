package com.ats.rusaadmin.model;
 

public class Page {
	 
	private int pageId; 
	private int templateId; 
	private int moduleId; 
	private String pageName; 
	private String pageSlug; 
	private String externalUrl; 
	private String typeSecCate; 
	private int secCateId; 
	private String pageMetaTitle; 
	private String pageMetaDescription; 
	private String pageMetaKeyword;
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageSlug() {
		return pageSlug;
	}
	public void setPageSlug(String pageSlug) {
		this.pageSlug = pageSlug;
	}
	public String getExternalUrl() {
		return externalUrl;
	}
	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}
	public String getTypeSecCate() {
		return typeSecCate;
	}
	public void setTypeSecCate(String typeSecCate) {
		this.typeSecCate = typeSecCate;
	}
	public int getSecCateId() {
		return secCateId;
	}
	public void setSecCateId(int secCateId) {
		this.secCateId = secCateId;
	}
	public String getPageMetaTitle() {
		return pageMetaTitle;
	}
	public void setPageMetaTitle(String pageMetaTitle) {
		this.pageMetaTitle = pageMetaTitle;
	}
	public String getPageMetaDescription() {
		return pageMetaDescription;
	}
	public void setPageMetaDescription(String pageMetaDescription) {
		this.pageMetaDescription = pageMetaDescription;
	}
	public String getPageMetaKeyword() {
		return pageMetaKeyword;
	}
	public void setPageMetaKeyword(String pageMetaKeyword) {
		this.pageMetaKeyword = pageMetaKeyword;
	}
	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", templateId=" + templateId + ", moduleId=" + moduleId + ", pageName="
				+ pageName + ", pageSlug=" + pageSlug + ", externalUrl=" + externalUrl + ", typeSecCate=" + typeSecCate
				+ ", secCateId=" + secCateId + ", pageMetaTitle=" + pageMetaTitle + ", pageMetaDescription="
				+ pageMetaDescription + ", pageMetaKeyword=" + pageMetaKeyword + "]";
	}
	
	

}
