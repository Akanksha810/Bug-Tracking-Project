// Authors : Akanksha Shrivastava,Adrija Ghansiyal, Abhijeet Nitin Raut,Gautami Ravindra Rahane
// Purpose : Interface for database layer (dao layer)
package com.code.dao;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;

public interface BugTrackDao {

	//function to import users from a JSON file
	int importUsers(List<User> userList);

	//function to list all projects under a user
	List<Project> getAllProjects(int userid);

	//function to list the bugs in a project acc. to user
	List<Bug> getAllBugs(int projectid);

	//Function to add remarks to the bug and mark it for closing
	boolean addRemarks(Bug bug);

	

}
