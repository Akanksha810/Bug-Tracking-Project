//Author : Akanksha Shrivastava, Adrija Ghansiyal, Abhijeet Nitin Raut,Gautami Ravindra Rahane
// Purpose : Provide business logic to the bug tracking service

package com.code.service;

import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;
import com.code.dao.BugTrackDao;
import com.code.dao.BugTrackDaoImpl;

public class BugTrackServiceImpl implements BugTrackService{

	private BugTrackDao bugTrackDao;
	//initialize the bugTrackDao object
	public BugTrackServiceImpl(){
		super();
		bugTrackDao = new BugTrackDaoImpl();
		
	}
	
		
	//to get all projects under a user id<-----called in DisplayProjectServlet
	@Override
	public List<Project> getAllProjects(int userid) {
		return bugTrackDao.getAllProjects(userid);
	}


	// Function to save imported users into database
	@Override
	public int importUsers(List<User> userList) {
		return bugTrackDao.importUsers(userList);
	}


	// Function to get the bug List of a project 
	@Override
	public List<Bug> getAllBugs(int projectid) {
		return bugTrackDao.getAllBugs(projectid);
	}

	//Function to add remarks to the bug and mark it for closing
	@Override
	public boolean addRemarks(Bug bug) {
		return bugTrackDao.addRemarks(bug);
		
	}
	
}
