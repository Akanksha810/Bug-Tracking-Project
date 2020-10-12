# Bug-Tracking-Project
Code Fury Challenge!

Final commmit ID : https://github.com/Akanksha810/Bug-Tracking-Project/commit/1228b99ad166b97010d9eb621ffff5ce9ead0a6f

Tools used :-

Java development Kit = jdk 1.8

Apache Tomcat verison 9

Derby database version 10.13.1.1


jar files used :-

1. derby.jar - Since derby database is used, this is require for database connectivity.

2. apache commons - These jars are for the file upload functionality. The json file is uploaded to the server for importing users. The files are then read from the request object.

3. gson.jar - It is used to convert the json text into java object.


Configurations :-

1. Set the JAVA_HOME variable to the relevant jdk path and make sure that its bin folder is added to the path variable.

2. Set the DERBY_HOME variable and its path value as folder where Derby_10.13.1.1 is extracted. Also add its bin folder's path value to the path variable.

3. Similarly, Set the TOMCAT_HOME variable and its path value as the folder where tomcat is extracted.

4. Import the project to the Spring tool suite workspace and make sure that the build path of the project contains all the above mentioned jar files and libraries. If not, you can add them manually from your directory.

5. Deploy the project on tomcat server in STS to run the project.


Functionalities of various modules :-

(A) Bean classes

1. User - User class, parent of ProjectManager, Tester and Developer, contains details about all users.
2. Bug - Bug class that stores info about bugs.
3. Project - Project class containing details about the project.
4. Developer - Developer class, child of User, to store data about developers.
5. Project Manager - Project Manager class, child class for User, to store details about project managers.
6. Tester - Tester class, child of User, for details about tester.

(B) Dao layer classes and Interfaces

1. BugTrackDao - Interface for database layer (Dao layer) having function definitions of the layer.
2. BugTrackDaoImpl - This class contains the implementation of the functions defined in the BugTrackDao Interface.
3. DBUtil - provides singleton factory pattern for jdbc.
4. RegisterDao - Interface for registration.
5. RegisterDaoImpl - Implementation of registration.
6. UserDao - Interface for database layer (dao layer).
7. UserDaoImpl - Establish connection with derby db & run queries to fetch/add/modify data.


(C) Exceptions classes

1. BugNotFoundException - Exception when a bug is not found.
2. ErrorInExecutionException - Exception for handling error in execution of code.
3. InvalidUserException - Exception when user is invalid.
4. UserNotFoundException - Exception when the user is not found.

(D) Service layer classes and Interfaces

1. BugTrackService - Interface for business logic layer (service layer).
2. BugTrackServiceImpl - Provide business logic to the bug tracking service.
3. RegisterService - Interface for business logic for registration.
4. RegisterServiceImpl - Provides business logic for registration.
5. UserService - Interface for service layer.
6. UserServiceImpl - class for service layer.

(E) Servlets

1. ImportUsersServlet - For importing users to the database.
2. MyLoginServlet -  servlet for login (session created).
3. LogoutServlet -  To logout of the system (session invalidated).
4. RegisterServlet - For registering a user.
5. AddProject - Adding of new project by manager.
6. PMCreateProject - For creating a new project and transferring to CreateProject.jsp for getting info about the project.
7. DisplayPMBugsServlet - To display bug list for the project chosen by the project manager.
8. DisplayPMProjectsServlet - To display the projects created by the project manager.
9. PMAssign - For assigning the developer to a bug.
10. PMCloseBug - For closing the bugs marked by the developers.
11. AddRemarks - Servlet to add remarks when developer marks a bug for closing.
12. AssignDevServlet - To get the list of developers for assigning them to a bug.
13. DisplayDeveloperBugsServlet - Servlet to display bugs from a specific project.
14. DisplayDeveloperProjectsServlet - To display projects assigned to the developer.
15. DisplayTesterBugsServlet - To display bugs of the project chosen by the tester.
16. DisplayTesterProjectsServlet - To display projects assigned to the tester.
17. GetBugInfoServlet - Used to get info about the new bug from addbug.jsp form.
18. TempBugServlet - For redirecting the page to AddNewBug.jsp and autofilling some details.


Database Queries:-

connect 'jdbc:derby: C:\bugtrackproject\bugtrackdb;create=true;user=hsbc;password=hsbc123';

connect 'jdbc:derby: C:\bugtrackproject\bugtrackdb;user=hsbc;password=hsbc123';

__________________________________________________________________________________

create table usertable (userid int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1), name varchar(25), email varchar(30), type varchar(15), noOfProjects int);

create table logintable (email varchar(30) primary key, password varchar(20), userid int unique references usertable(userid), lastlogindate date,lastlogintime time);

create table projecttable(projectid int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 100, Increment by 1), projectname varchar(20),description varchar(100), startdate date, status varchar(15), managerid int references usertable(userid));

create table teamtable(projectid int references projecttable(projectid),userid int references usertable(userid), managerid int references usertable(userid));

create table bugtable (uniqueid int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1000, Increment by 1), title varchar(20),description varchar(50), projectid int references projecttable(projectid), createdby int references usertable(userid), opendate date,assignedto int references usertable(userid), markedforclosing varchar(6), closedby int references usertable(userid),closedon date, status varchar(10),severitylevel varchar(10), remarks varchar(40));




Team Members:

Abhijeet Nitin Raut

Adrija Ghansiyal

Akanksha Shrivastava

Akshay Ramdev

Ankit D.S Bhadoriya

Bharadwaj Divate Kondandarama

Dharampreet Singh

Gautami Ravindra Rahane

Hrishita Bhattacharjee

Hrushikesh Vitthal Vanga
