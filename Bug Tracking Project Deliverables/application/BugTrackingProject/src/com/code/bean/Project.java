//Author : Akanksha
//Purpose : Project class containing details about the project.

package com.code.bean;

import java.util.Date;

public class Project {

	//Data members
	private int projectId;
	private String projectName;
	private String description;
	private Date startDate;
	private String status;	//In-progress/completed
	private int managerId;
	
	//default constructor
	public Project() {
		
		projectId = 0;
		projectName = null;
		description = null;
		startDate = null;
		status = null;
		managerId=0;
	}
	
	//parameterized constructor
	public Project(int projectId, String projectName, String description, Date startDate, String status,int managerId) {
		
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.status = status;
		this.managerId=managerId;
		
	}

	
	//setters and getter methods
	public int getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	// //Overriding toString method
	// @Override
	// public String toString() {
	// 	return "Project ID = " + projectId + "\nProject Name = " + projectName + "\nDescription = " + description
	// 			+ "\nStart Date = " + startDate + "\nStatus = " + status+"\nManager Id= "+managerId;
	// }
	
}
