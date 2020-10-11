// Authors : Akanksha, Adrija, Abhijeet, Dharampreet, Hrishita, Gautami, Bharadwaj
// Purpose : Interface for database layer (dao layer)
package com.code.dao;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;
import com.code.exception.*;

public interface BugTrackDao {

	//function to import users from a JSON file
	int importUsers(List<User> userList);

	//function to list all projects under a user
	List<Project> getAllProjects(int userid);

	//function to list the bugs in a project acc. to user
	List<Bug> getAllBugs(int projectid);
	
	//function to list users under a manager
	List<User> getUsersByManager(Integer managerId);

	//function to fetch project list for the project manager
	List<Project> getAllPMProjects(int managerid);

	//function to close a bug
	boolean closeBug(int bugId) throws BugNotFoundException;

	//function to assign a developer to a bug
	boolean assignDev(int bugId, int managerId)throws UserNotFoundException;

	//function to add aaa new bug
	void addNewBug(Bug bug) throws ErrorInExecutionException;

	//function to update no.of projects of a user
	void updateNoOfProjects(int[] userIdChecked);

	//function to list all employees
	List<User> getAllEmployees(int mgrId);

	//function to add a new project
	boolean addProject(Project p) throws ErrorInExecutionException;

	//function to add users to a project
	void addUsersToProject(String projName, int[] userIdChecked, int managerid) throws ErrorInExecutionException;

	//function to add remarks to the bug and mark it for closing
	boolean addRemarks(Bug bug) throws ErrorInExecutionException;

	//function to list all bugs of a project
	List<Bug> getAllDevBugs(int projectId, int userId) throws ErrorInExecutionException;

}