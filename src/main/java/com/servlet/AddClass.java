package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.ClassMaster;
import com.entity.Student;
import com.util.HibernateUtil;

@WebServlet("/add-class")
public class AddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		try {
			if((boolean)session.getAttribute("loggedin")) {
				request.getRequestDispatcher("addclass.html").forward(request, response);
			}
			else {
				request.getRequestDispatcher("index.html").forward(request, response);
			}
		}
		catch(Exception e) {
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		//System.out.println("hhhh");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session1 = request.getSession(true);
		try {
			if((boolean)session1.getAttribute("loggedin")) {
				ClassMaster cls = new ClassMaster(0,request.getParameter("clsname"));

				// Populate Student object
				

				// Step 2: Initialize hibernate to psersist object
				SessionFactory sf = HibernateUtil.buildSessionFactory();
				Session session = sf.openSession();

				Transaction tx = session.beginTransaction();
				
				String clsname = request.getParameter("clsname");
				cls.setClass_name(clsname);
				session.save(cls);
				tx.commit();

				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("Data successfully inserted in DB");
				out.println("</body></html>");
			}
			else {
				request.getRequestDispatcher("index.html").forward(request, response);
			}
		}
		catch(Exception e) {
			request.getRequestDispatcher("index.html").forward(request, response);
		}

		
	}
}
