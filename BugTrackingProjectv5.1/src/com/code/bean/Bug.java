//Author : Akanksha
//Purpose : Bug class that stores info about bugs.

package com.code.bean;

import java.util.Date;

public class Bug {

	//data members
	private int bugId;
	private String bugTitle;
	private	String bugDescription;
	private int projectId;
	private int createdBy;
	private Date openDate;
	private int assignedTo;
	private String markedForClosing;
	private int closedBy;
	private Date closedOn;
	private String status;	// open/closed
	private String severityLevel;	// critical/major/minor/trivial
	private String remarks;
	
	//default constructor
	public Bug() {
		
		bugId = 0;
		bugTitle = null;
		bugDescription = null;
		projectId = 0;
		createdBy = 0;
		openDate = null;
		assignedTo = 0;
		markedForClosing = null;
		closedBy = 0;
		closedOn = null;
		status = "closed";
		severityLevel = "trivial";
		remarks=null;
	}
	
	//parameterized constructor
	public Bug(int bugId, String bugTitle, String bugDescription,int projectId, int createdBy, Date openDate, int assignedTo,
			String markedForClosing, int closedBy, Date closedOn, String status, String severityLevel,String remarks) {
		super();
		this.bugId = bugId;
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
		this.projectId = projectId;
		this.createdBy = createdBy;
		this.openDate = openDate;
		this.assignedTo = assignedTo;
		this.markedForClosing = markedForClosing;
		this.closedBy = closedBy;
		this.closedOn = closedOn;
		this.status = status;
		this.severityLevel = severityLevel;
		this.remarks=remarks;
	}

	
	//setter and getter methods
	public int getBugId() {
		return bugId;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public String getBugDescription() {
		return bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public int getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getMarkedForClosing() {
		return markedForClosing;
	}

	public void setMarkedForClosing(String markedForClosing) {
		this.markedForClosing = markedForClosing;
	}

	public int getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(int closedBy) {
		this.closedBy = closedBy;
	}

	public Date getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	// //Overriding toString method
	// @Override
	// public String toString() {
	// 	return "Bug ID = " + bugId + "\nBug Title = " + bugTitle + "\nBug Description = " + bugDescription + "\nProject ID = "+projectId + "\nCreated By = "+ createdBy + "\nOpen Date = " + openDate + "\nAssigned To = " + assignedTo + "\nMarked For Closing = "+ markedForClosing + "\nClosed By = " + closedBy + "\nClosed On = " + closedOn + "\nStatus = " + status
	// 			+ "\nSeverity Level = " + severityLevel+"\n remarks: "+remarks;
	// }	
}
