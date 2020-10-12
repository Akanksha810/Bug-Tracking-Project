// Authors : Akanksha, Adrija, Abhijeet, Dharampreet, Hrishita, Gautami, Bharadwaj
//Purpose : Interface for business logic layer (service layer)

package com.code.service;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;
import com.code.exception.*;

public interface BugTrackService {

	//Method to call dao layer and save imported users into db
	int importUsers(List<User> userList);
		
	//Method to get the list of all projects acc. to user ID
	List<Project> getAllProjects(int userid);
	
	//Method to get the list of bugs acc. to user ID and project ID
	List<Bug> getAllBugs(int projectid);

	//Method to get the list of developers and testers under a manager
	List<User> getUsersByManager(Integer managerId);

	//Method to get all projects of the project manager
	List<Project> getAllPMProjects(int managerid);

	//Method to close a bug
	boolean closeBug(int bugId)throws BugNotFoundException;
	
	//Method to assign a bug to developer
	boolean assignDev(int bugId, int managerId)throws UserNotFoundException;

	//Method to add a new bug
	void addBug(Bug bug) throws ErrorInExecutionException;

	//Method to get all free employees
	List<User> getAllEmployees(int mgrId);
	
	//To add a new project
	boolean addProject(Project project) throws ErrorInExecutionException;
	
	//To update the number of projects under a user
	void updateNoOfProjects(int[] userIdChecked) ;
	
	//To users to Team
	void addToTeam(String projectName,int[] userIdChecked,int managerid) throws ErrorInExecutionException;

	//Method to add remarks to the bug and mark it for closing
	boolean addRemarks(Bug bug) throws ErrorInExecutionException;

	//Method to list all bugs assigned to a developer
	List<Bug> getAllDevBugs(int projectId, int userId) throws ErrorInExecutionException;



	

}
