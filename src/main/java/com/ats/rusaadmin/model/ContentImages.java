package com.ats.rusaadmin.model;

public class ContentImages {
	
	private String image;
	private String thumb;
	private String folder;
	private String type;
	private String size;
	private String lastmod;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getLastmod() {
		return lastmod;
	}
	public void setLastmod(String lastmod) {
		this.lastmod = lastmod;
	}
	@Override
	public String toString() {
		return "ContentImages [image=" + image + ", thumb=" + thumb + ", folder=" + folder + ", type=" + type
				+ ", size=" + size + ", lastmod=" + lastmod + "]";
	}
	
	
}
