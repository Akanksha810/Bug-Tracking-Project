// Authors : Akanksha, Adrija, Abhijeet, Dharampreet, Hrishita, Gautami, Bharadwaj
// Purpose : Establish connection with derby db & run queries to fetch/add/modify data

package com.code.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;
import com.code.dao.DBUtil;
import com.code.exception.BugNotFoundException;
import com.code.exception.ErrorInExecution;
import com.code.exception.UserNotFoundException;


public class BugTrackDaoImpl implements BugTrackDao {
	
	static Connection conn;
	static PreparedStatement pgetAllProjects,pgetAllBugs,inserimportedusers,pgetAllPMProjects,closebug,assigndev,pgetmembers,pinsBug,
	pgetAllDevelopers,pgetAllTesters,pgetAssignedTesters,pins,pgetUser,pinsNewProject,pupdateProject,pgetProjName,addremarks,
	pmarkForClosing,pgetAllDevBugs;
	//preparing queries...
	static {
		conn=DBUtil.getMyConnection();
		try {
			//write preparedStatements here
			
			//to import users from json to db
			inserimportedusers = conn.prepareStatement("insert into usertable values(DEFAULT,?,?,?,0)");

			//to get the project details based on the user of the team member involved
			pgetAllProjects=conn.prepareStatement("select * from projecttable p join teamtable t on p.projectid=t.projectid where t.userid=?");

			//to get the project details for the prject manager
			pgetAllPMProjects=conn.prepareStatement("select * from projecttable where managerid=?");
			
			//get team members under a manager
			pgetmembers=conn.prepareStatement("select u.userid,name from usertable u join teamtable t on u.userid=t.userid where u.type='developer' and t.managerid=?");
			
			//to display bug details as per its project id
			pgetAllBugs = conn.prepareStatement("select * from bugtable where projectid = ? and status='open'");

			//to display bugs specific to a developer
			pgetAllDevBugs = conn.prepareStatement("select * from bugtable where projectid = ? and markedforclosing in ('false','null') and assignedTo=?");

			//to close the bug
			closebug= conn.prepareStatement("update bugtable set status='close' where uniqueId=?");

			//assign dev
			assigndev=conn.prepareStatement("update bugtable set assignedto=? where uniqueid=?");

			//report new bug
			pinsBug = conn.prepareStatement("insert into bugtable(title,description,projectid,createdby,opendate,markedforclosing,status,severitylevel) values(?,?,?,?,?,'false',?,?)");
			
			//to display list of developers who are free to take up projects 
			pgetAllDevelopers=conn.prepareStatement("select * from usertable where type='developer' and noOfProjects<1");

			//to display all testers who are free to take up projects
			pgetAllTesters=conn.prepareStatement("select * from usertable where type='tester' and noOfProjects<1");

			//to display testers who are free to take up projects under a specific project manager 
			pgetAssignedTesters=conn.prepareStatement("select u.* from usertable u join teamtable t on u.userid=t.userid where u.type='tester' and u.noofprojects=1 and t.managerid=?");

			//add rows in project table
			pins=conn.prepareStatement("insert into projecttable values(default,?,?,?,?,?)");

			//to get a project id from project name
			pgetProjName=conn.prepareStatement("select projectid from projecttable where projectname=?");

			//get all the users coming under a new project
			pgetUser=conn.prepareStatement("select * from usertable  where userid=?");

			//update the no. of projects under a given user
			pupdateProject=conn.prepareStatement("update usertable set noOfProjects=? where userid=?");

			//To insert new project to the teamtable
			pinsNewProject=conn.prepareStatement("insert into teamtable values(?,?,?)");
			
			//to add remarks in bug table after marking bug for closing
			addremarks = conn.prepareStatement("update bugtable set remarks=? where uniqueid=?");

			//to mark a bug for closing
			pmarkForClosing=conn.prepareStatement("update bugtable set markedforclosing='true' where uniqueid=?");
		
			
		} catch (SQLException e) {
			
			System.out.println("Connection error : Can't reach the Database");
			//e.printStackTrace();
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

				System.out.println("Import users failed");
				//e.printStackTrace();
			}
		}
		return 0;
	}

	//Function to fetch projects based on userid(developer/Tester)	
	@Override
	public List<Project> getAllProjects(int userid) {

		List<Project> projectList=new ArrayList<>();
		try {
			pgetAllProjects.setInt(1, userid);
			ResultSet rs=pgetAllProjects.executeQuery();
			while(rs.next()) {
				Project p=new Project(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getInt(6));
				projectList.add(p);
			}
			return projectList;
		} catch (SQLException e) {

			System.out.println("Failed to retreive project list");
			//e.printStackTrace();
		}
		return null;
	}

	//Function to fetch project list for the project manager
	public List<Project> getAllPMProjects(int mgrid) {

		List<Project> projectList=new ArrayList<>();
		try {
			pgetAllPMProjects.setInt(1, mgrid);
			//System.out.print("in dao");
			ResultSet rs=pgetAllPMProjects.executeQuery();
			while(rs.next()) {
				Project p=new Project(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),mgrid);
				projectList.add(p);
			}
			return projectList;
		} catch (SQLException e) {

			System.out.println("Failed to retrieve project list");
			//e.printStackTrace();
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
				Bug bug=new Bug(rs.getInt(1),rs.getString(2),rs.getString(3),projectid,rs.getInt(5),rs.getDate(6),rs.getInt(7),
						rs.getString(8),rs.getInt(9),rs.getDate(10),rs.getString(11),rs.getString(12),rs.getString(13));
				bugList.add(bug);
			}
			return bugList;
		} catch (SQLException e) {

			System.out.println("Failed to get the list of bugs");
			//e.printStackTrace();
		}
		return null;
	}

	//function to return list of users under a manager
	@Override
	public List<User> getUsersByManager(Integer managerId) {

		List<User> ulist=new ArrayList<>();
		
		try {
			pgetmembers.setInt(1, managerId);
			ResultSet rs=pgetmembers.executeQuery();
			while(rs.next()) {
				User u=new User(rs.getInt(1),rs.getString(2),null,null);
				ulist.add(u);
				
			}
			return ulist;
		} catch (SQLException e) {
			
			System.out.println("Failed to get the list of users");
			//e.printStackTrace();
		}
		return null;
	}

	//Function to close a bug
	@Override
	public boolean closeBug(int bugId)throws BugNotFoundException {
		try {
			closebug.setInt(1, bugId);
			int n=closebug.executeUpdate();
			if(n!=0)
				return true;
			else
				throw new BugNotFoundException("Bug does not exist !");
		} catch (SQLException e) {
		
			System.out.println("Error in closing the bug");
			//e.printStackTrace();
			return false;
		}
		
	}

	//assigning developer to bug by manager
	@Override
	public boolean assignDev(int bugId, int userId)throws UserNotFoundException {
		try {
		assigndev.setInt(1, userId);
		assigndev.setInt(2, bugId);
		int n= assigndev.executeUpdate();
		if(n!=0)
			return true;
		else {
			throw new UserNotFoundException("User not found ! Please try again !");

		}
		}catch (SQLException e) {

			System.out.println("Error in assigning the bug to the developer. Please try again.");
			//e.printStackTrace();
			return false;
		}


	}

	//Function to add the reported bug to database
	@Override
	public void addNewBug(Bug bug) throws ErrorInExecutionException {
		try {
			System.out.println(bug);
			java.sql.Date date = new java.sql.Date((bug.getOpenDate()).getTime());
			System.out.println(date);
			System.out.println(bug.getBugTitle());
			pinsBug.setString(1, bug.getBugTitle());
			pinsBug.setString(2, bug.getBugDescription());
			pinsBug.setInt(3, bug.getProjectId());
			pinsBug.setInt(4, bug.getCreatedBy());
			pinsBug.setDate(5, date);
			pinsBug.setString(6, bug.getStatus());
			pinsBug.setString(7, bug.getSeverityLevel());
			
			int n=pinsBug.executeUpdate();
			if(n<=0) {
				throw new  ErrorInExecutionException("Error occured while executing the query");
			}
		} catch (SQLException e) {
			
			System.out.println("Error in updating the new bug.");
			//e.printStackTrace();
		}
		
	}
	
	//To get all free employees
	public List<User> getAllEmployees(int mgrId){

		List<User> userList = new ArrayList<>();
		try {
			
			ResultSet rs1=pgetAllDevelopers.executeQuery();
			while(rs1.next()) {
				User user=new User(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getString(4));
				userList.add(user);
			}
			
			ResultSet rs2=pgetAllTesters.executeQuery();
			while(rs2.next()) {
				User user=new User(rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4));
				userList.add(user);
			}
			pgetAssignedTesters.setInt(1, mgrId);
			ResultSet rs3=pgetAssignedTesters.executeQuery();
			while(rs3.next()) {
				User user=new User(rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4));
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {

			System.out.println("Failed to retreive the list");
			e.printStackTrace();
		}
		return null;
	}
		
	//To add a new project
	public boolean addProject(Project p) throws ErrorInExecutionException {
		try {
			java.sql.Date date = new java.sql.Date((p.getStartDate()).getTime());
//			pins.setInt(1, p.getProjectId());
			pins.setString(1, p.getProjectName());
			pins.setString(2,p.getDescription());
			pins.setDate(3,date);
			pins.setString(4,p.getStatus());
			pins.setInt(5,p.getManagerId());
			int i=pins.executeUpdate();
			if(i>0) {
				return true;
			}
			else {
				throw new  ErrorInExecutionException("Error occured while executing the query");
			}
		} catch (SQLException e) {
			
			System.out.println("Failed to add the project. Please try again.");
			//e.printStackTrace();
			return false;
		}
	}
	
	//Function to update no. of projects for a user
	@Override
	public void updateNoOfProjects(int[] userIdChecked) {
		/*
		 * for(int i=0;i<checked.length;i++) { pNoOfProjects.setInt(1,checked[i]);
		 * pupdate.setInt(2,p.getQty());
		 */
		int numproject=0;
		ResultSet rs;
		for(int id:userIdChecked) {
			try {
				pgetUser.setInt(1, id);
				rs=pgetUser.executeQuery();
				while(rs.next()) {
					numproject=rs.getInt(5);
					numproject+=1;
				}
				pupdateProject.setInt(1, numproject);
				pupdateProject.setInt(2, id);
				pupdateProject.executeUpdate();
			} catch (SQLException e) {
				
				System.out.println("Failed to update the no. of projects for the user");
				//e.printStackTrace();
			}
		}
	}
	
	//To add users to the team
	@Override
	public void addUsersToProject(String projectName,int[] userIdChecked,int managerid) throws ErrorInExecutionException {
		for(int id:userIdChecked) {
			try {
				pgetProjName.setString(1, projectName);
				int projectId=0;
				ResultSet rs=pgetProjName.executeQuery();
				while(rs.next()) {
					projectId=rs.getInt(1);
				}
				pinsNewProject.setInt(1, projectId);
				pinsNewProject.setInt(2, id);
				pinsNewProject.setInt(3, managerid);
				int i=pinsNewProject.executeUpdate();
				if(i<=0) {
					throw new  ErrorInExecutionException("Error occured while executing the query");
					
				}
			} catch (SQLException e) {
				
				System.out.println("Failed to add users to the project.");
				//e.printStackTrace();
			}
			
		}
		
	}

	//Function to add remarks to the bug and mark it for closing
	@Override
	public boolean addRemarks(Bug bug) throws ErrorInExecutionException {
		try {
			//System.out.println(bug.getRemarks());
			addremarks.setString(1,bug.getRemarks());
			addremarks.setInt(2, bug.getBugId());
			int n = addremarks.executeUpdate();
			
			if(n>=0) {
				pmarkForClosing.setInt(1, bug.getBugId());
				pmarkForClosing.executeUpdate();
				return true;
			}
				
			else {
				throw new  ErrorInExecutionException("Error occured while executing the query");
			//	return false;
			}
		} catch (SQLException e) {

			System.out.println("Failed to mark te bug for closing, please try again.")
			//e.printStackTrace();
		}
		return false;
	}

	//Function to list all the bugs assigned to a developer
	@Override
	public List<Bug> getAllDevBugs(int projectId,int userId) throws ErrorInExecutionException {

		List<Bug> bugList = new ArrayList<Bug>();

		//boolean flag=false;
		try {
			pgetAllDevBugs.setInt(1, projectId);
			pgetAllDevBugs.setInt(2, userId);
			ResultSet rs=pgetAllDevBugs.executeQuery();
			while(rs.next()) {
				Bug bug=new Bug(rs.getInt(1),rs.getString(2),rs.getString(3),projectId,rs.getInt(5),rs.getDate(6),rs.getInt(7),
						rs.getString(8),rs.getInt(9),rs.getDate(10),rs.getString(11),rs.getString(12),rs.getString(13));
				 bugList.add(bug);
			}
			return bugList;
		} catch (SQLException e) {

			throw new  ErrorInExecutionException("Error occured while executing the query");
		}
	}
}
