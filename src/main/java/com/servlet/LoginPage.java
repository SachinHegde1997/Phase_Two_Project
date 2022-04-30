package com.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/login")
public class LoginPage extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
      
        HttpSession session = request.getSession();
        if(uname.equals("Admin") && pass.equals("Admin@123"))
        {
        	session.setAttribute("loggedin", true);
        	request.getRequestDispatcher("home.html").forward(request, response);
        	
        	
        }
        else
        {
        	session.setAttribute("loggedin", false);
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        	
        }
    } 
}
