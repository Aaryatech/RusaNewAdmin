package com.ats.rusaadmin.model;



public class SiteMaitenance {

	private int settingId;
	
	private String group;
	
	private String key;
	
	private String value;
	
	private int serialized;
	
	private int editable;

	public int getSettingId() {
		return settingId;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getSerialized() {
		return serialized;
	}

	public void setSerialized(int serialized) {
		this.serialized = serialized;
	}

	public int getEditable() {
		return editable;
	}

	public void setEditable(int editable) {
		this.editable = editable;
	}

	@Override
	public String toString() {
		return "SiteMaitenance [settingId=" + settingId + ", group=" + group + ", key=" + key + ", value=" + value
				+ ", serialized=" + serialized + ", editable=" + editable + "]";
	}
	
	
	
}
