package com.ralucamafteciuc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ralucamafteciuc.database.DBConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("login.html");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DBConnection dbConnection = new DBConnection();		
		
		//set response headers
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		//create HTML response
		PrintWriter writer = response.getWriter();
		
		writer.append("<!DOCTYPE html>")
		.append("<html><head>")
		.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>")
		.append("<title>Processing login</title></head>");
		
		try {
			if(dbConnection.checkCredentialsForLogin(username, password, "users")) {
				RequestDispatcher view = request.getRequestDispatcher("/home?username=" + username);
				view.forward(request, response);
			} else {
				writer.append("<body><div style=\"width:600px; margin:0 auto;\"><h2>Login failed!</h2>")
				.append("<h3>Wrong username or password!</h3>")
				.append("<input type=\"button\" value=\"Try again\" onclick=\"window.location.replace('/Curs5-WebApp/login')\">");				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
        writer.append("</div>");
		writer.append("</body></html>");	
	}
}
