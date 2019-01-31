package com.ats.rusaadmin.model;
 

public class ModulesNames {
	 
	private int id; 
	private String name; 
	private String multipleRepeat; 
	private int status; 
	private String talesName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMultipleRepeat() {
		return multipleRepeat;
	}
	public void setMultipleRepeat(String multipleRepeat) {
		this.multipleRepeat = multipleRepeat;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTalesName() {
		return talesName;
	}
	public void setTalesName(String talesName) {
		this.talesName = talesName;
	}
	@Override
	public String toString() {
		return "ModulesNames [id=" + id + ", name=" + name + ", multipleRepeat=" + multipleRepeat + ", status=" + status
				+ ", talesName=" + talesName + "]";
	}
	
	

}
