//Author : Akanksha Shrivastava, Adrija Ghansiyal, Abhijeet Nitin Raut, Gautami Ravindra Rahane
//Purpose : Interface for business logic layer (service layer)

package com.code.service;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;

public interface BugTrackService {

	//Method to call dao layer and save imported users into db
		int importUsers(List<User> userList);
		
	//Method to get the list of all projects acc. to user ID
	List<Project> getAllProjects(int userid);
	
	//Method to get the list of bugs acc. to user ID and project ID
	List<Bug> getAllBugs(int projectid);
	
	//Method to add remarks to the bug and mark it for closing
	boolean addRemarks(Bug bug);

	
	

}
