// Author : Abhijeet Raut
//Purpose : For importing users.
package com.code.servlet;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.bean.User;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class ImportUsersServlet
 */
public class ImportUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ImportUsersServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");

		if (ServletFileUpload.isMultipartContent(request)) {

			ServletFileUpload serverFileUpload = new ServletFileUpload(new DiskFileItemFactory());
			try {
				List<FileItem> files = serverFileUpload.parseRequest(request);
				for (FileItem fileItem : files) {
					BufferedReader br = new BufferedReader(new InputStreamReader(fileItem.getInputStream(), "UTF-8"));
					String content = "";
					String line = "";
					while ((line = br.readLine()) != null) {
					    if (line.isEmpty()) {
					        break;
					    }
					    content = content + line;
					}
					
					BugTrackService bugTrackService = new BugTrackServiceImpl();
					List<User> userList = new ArrayList<>();
					Gson gson = new Gson();
					JsonParser jsonParser = new JsonParser();
					JsonElement jsonElement = jsonParser.parse(content);
					
					if(jsonElement.isJsonObject()) {
						
						User user = gson.fromJson(jsonElement, User.class);
						userList.add(user);	
					}
					else if(jsonElement.isJsonArray()) {

						System.out.println("Array");
						Type collectionType = new TypeToken<List<User>>(){}.getType();
						userList = (List<User>) new Gson().fromJson( jsonElement , collectionType);
						System.out.println(userList);
					}
					
					int result = bugTrackService.importUsers(userList);
					out.println(result + " user/s inserted");
					RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
					rd.include(request, response);
				}
			} catch (FileUploadException e) {

				out.println("Error in uploading file");
				//e.printStackTrace();
			} catch(JsonSyntaxException e1) {
				out.println("Not a valid json syntax");
				RequestDispatcher rd=request.getRequestDispatcher("importuser.jsp");
				rd.include(request, response);
			}
		}		
		
	}

}