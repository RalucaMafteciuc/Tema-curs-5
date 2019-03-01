package com.ralucamafteciuc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeHandler
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		if (username == null || username.trim().isEmpty()) {
			username = "Visitor";
		}
		
		//set response headers
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		//create HTML response
		PrintWriter writer = response.getWriter();
		
		writer.append("<!DOCTYPE html>")
		.append("<html><head>")
		.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>")
		.append("<title>Welcome message</title></head>")
		.append("<body><div style=\"width:600px; margin:0 auto;\"><h2>Welcome, " + username + "</h2>")		
		.append("</div>")
		.append("</body></html>");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
