package com.ralucamafteciuc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ralucamafteciuc.database.DBConnection;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		DBConnection dbConnection = new DBConnection();		
		int hasEntered = 0;
		
		//set response headers
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		//create HTML response
		PrintWriter writer = response.getWriter();
		
		writer.append("<!DOCTYPE html>")
		.append("<html><head>")
		.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>")
		.append("<title>Welcome message</title></head>")
		.append("<body><div style=\"width:600px; margin:0 auto;\"><h2>You have entered</h2>");			
		
		if (username == null || username.trim().isEmpty()) {
			writer.append("<p>Name: MISSING</p>");
		} else
			try {
				if(dbConnection.checkIfUserExists("users", username)){
					writer.append("<p>Name: Username is already taken!</p>");
				}else {
					writer.append("<p>Name: " + username + "</p>");
					hasEntered++;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		if (email == null || email.trim().isEmpty()) {
			writer.append("<p>Email: MISSING</p>");
		} else {
			writer.append("<p>Email: " + email + "</p>");
			hasEntered++;
		}		
		
		if (password == null || password.trim().isEmpty()) {
			writer.append("<p>Password: MISSING</p>");
		} else {
			writer.append("<p>Password: is sensitive</p>");
			hasEntered++;
		}		
		
		if(hasEntered < 3)
			// Hyperlink "BACK" to input page
			writer.append("<input type=\"button\" value=\"Try again\" onclick=\"window.location.replace('/Curs5-WebApp/');\"/>");				
		else {
			writer.append("<h3 style=\"font-style:italic\">Succesful registration!</h3>");
			try {
				dbConnection.insertExample(username, password, email, "users");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
        writer.append("</div>");
		writer.append("</body></html>");				
	}
}