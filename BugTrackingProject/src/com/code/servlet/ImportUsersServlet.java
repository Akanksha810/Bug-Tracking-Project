// Author : Abhijeet Raut

package com.code.servlet;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
@MultipartConfig
public class ImportUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ImportUsersServlet() {
        super();
    }

    // Function to read json data, convert it to java object and store in database
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
			Part part = request.getPart("fileName");
			InputStream inputStream = part.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String fileContent = "";
			String line = "";
			while ((line = br.readLine()) != null) {
			    if (line.isEmpty()) {
			        break;
			    }
			    fileContent = fileContent + line;
			}
			
			BugTrackService bugTrackService = new BugTrackServiceImpl();
			List<User> userList = new ArrayList<>();
			Gson gson = new Gson();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(fileContent);
			
			if(jsonElement.isJsonObject()) {
				System.out.println("Object");
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
			System.out.println(result + " user/s inserted");
			
			out.println("Import successful : " + result + " users imported");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}catch(JsonSyntaxException e) {
			System.out.println("Invalid JSON Syntax");
			out.println("Invalid JSON Syntax");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		
	}

}
