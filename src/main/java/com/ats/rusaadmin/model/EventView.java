package com.ats.rusaadmin.model;


public class EventView {

	private int newsblogsId;

	private String heading; 
	
	private int moduleId;
	
	private String descriptions;
	
	private String addDate;
	
	private String eventLocation;
	
	private String eventDateFrom;
	
	private String eventContactPerson;
	
	private String eventContactNumber;
	
	private String editDate;

	private int exInt2;
	
	/*
	 * private int totalApplied;
	 * 
	 * private int totalApprove;
	 */

	
	public int getNewsblogsId() {
		return newsblogsId;
	}

	public void setNewsblogsId(int newsblogsId) {
		this.newsblogsId = newsblogsId;
	}


	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}


	
	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getEditDate() {
		return editDate;
	}

	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDateFrom() {
		return eventDateFrom;
	}

	public void setEventDateFrom(String eventDateFrom) {
		this.eventDateFrom = eventDateFrom;
	}

	public String getEventContactPerson() {
		return eventContactPerson;
	}

	public void setEventContactPerson(String eventContactPerson) {
		this.eventContactPerson = eventContactPerson;
	}

	public String getEventContactNumber() {
		return eventContactNumber;
	}

	public void setEventContactNumber(String eventContactNumber) {
		this.eventContactNumber = eventContactNumber;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	
	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	@Override
	public String toString() {
		return "EventView [newsblogsId=" + newsblogsId + ", heading=" + heading + ", moduleId=" + moduleId
				+ ", descriptions=" + descriptions + ", addDate=" + addDate + ", eventLocation=" + eventLocation
				+ ", eventDateFrom=" + eventDateFrom + ", eventContactPerson=" + eventContactPerson
				+ ", eventContactNumber=" + eventContactNumber + ", editDate=" + editDate + ", exInt2=" + exInt2 + "]";
	}

}
