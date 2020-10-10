// Author : Akanksha Shrivastava, Adrija Ghansiyal, Abhijeet Nitin Raut,Gautami Ravindra Rahane
// Purpose : Establish connection with derby db & run queries to fetch/add/modify data

package com.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;
import com.code.dao.DBUtil;


public class BugTrackDaoImpl implements BugTrackDao {
	static Connection conn;
	static PreparedStatement pgetAllProjects,pgetAllBugs,inserimportedusers,addremarks;
	//preparing queries...
	static {
		conn=DBUtil.getMyConnection();
		try {
			//write preparedStatements here
			
			//to import users from json to db
			inserimportedusers = conn.prepareStatement("insert into usertable values(DEFAULT,?,?,?)");
			//to get the project details based on the user of the team member involved
			pgetAllProjects=conn.prepareStatement("select * from projecttable p join teamtable t on p.projectid=t.projectid where t.userid=?");
			//to display bug details as per its project id
			pgetAllBugs = conn.prepareStatement("select * from bugtable where projectid = ? and status='open'");
			//to add remarks in bug table after marking bug for closing
			addremarks = conn.prepareStatement("update bugtable set remarks=? where bugid=?");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	// Function to save imported users into database
	@Override
	public int importUsers(List<User> userList) {
		int i = 1;
		for(User user : userList) {
			try {
				inserimportedusers.setString(1, user.getName());
				inserimportedusers.setString(2, user.getEmail());
				inserimportedusers.setString(3, user.getType());
				
				inserimportedusers.addBatch();
				
				if(i % 1000 == 0 || i == userList.size()) {
					int[] result = inserimportedusers.executeBatch();
					return result.length;
				}
				i++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	//Function to fetch projects based on userid	
	@Override
	public List<Project> getAllProjects(int userid) {
		
		try {
			pgetAllProjects.setInt(1, userid);
			ResultSet rs=pgetAllProjects.executeQuery();
			List<Project> projectList=new ArrayList<>();
			System.out.println("Inside try");
			while(rs.next()) {
				Project p=new Project(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),userid);
				projectList.add(p);
			}
			return projectList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Function to list bugs based on userid and projectid
	@Override
	public List<Bug> getAllBugs(int projectid) {
		List<Bug> bugList = new ArrayList<Bug>();
		try {
			pgetAllBugs.setInt(1, projectid);
			ResultSet rs=pgetAllBugs.executeQuery();
			while(rs.next()) {
				Bug bug=new Bug(rs.getInt(1),rs.getString(2),rs.getString(3),projectid,rs.getInt(5),rs.getDate(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getDate(10),rs.getString(11),rs.getString(12),rs.getString(13));
				bugList.add(bug);
			}
			return bugList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//Function to add remarks to the bug and mark it for closing
	@Override
	public boolean addRemarks(Bug bug) {
		try {
			System.out.println(bug.getRemarks());
			addremarks.setString(1,bug.getRemarks());
			addremarks.setInt(2, bug.getBugId());
			int n = addremarks.executeUpdate();
			if(n>0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
