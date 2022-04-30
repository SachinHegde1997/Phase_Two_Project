package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import com.dao.ClassDao;
import com.entity.ClassMaster;
import com.entity.Student;
import com.util.HibernateUtil;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		try {
			if((boolean)session.getAttribute("loggedin")) {
				ClassDao dao = new ClassDao();
				try {

					List<ClassMaster> classCatagory = dao.list();
					request.setAttribute("listCategory", classCatagory);
					request.getRequestDispatcher("addstudent.jsp").forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
			else {
				request.getRequestDispatcher("index.html").forward(request, response);
			}
		}
		catch(Exception e) {
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		
		

		// System.out.println("hhhh");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session1 = request.getSession(true);
		try {
			if((boolean)session1.getAttribute("loggedin")) {
				Student student = new Student();

				// Populate Student object
				populateStudent(request, student);

				// Step 2: Initialize hibernate to psersist object
				SessionFactory sf = HibernateUtil.buildSessionFactory();
				Session session = sf.openSession();

				Transaction tx = session.beginTransaction();
				session.save(student);
				tx.commit();

				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("Data successfully inserted in DB");
				out.println("<p> <a href='read-student'> Click Here </a> to show all student</p>");
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

	private void populateStudent(HttpServletRequest request, Student student) {
		try {
			String fname = request.getParameter("name");
			String address = request.getParameter("address");
			int class_id = Integer.parseInt(request.getParameter("class_id"));

			int rollno = getRollnum(class_id)+1;
			student.setAddress(address);
			student.setName(fname);
			student.setClass_id(class_id);
			student.setRoll_no(rollno);
		} catch (Exception e) {

		}

	}

	private int getRollnum(int class_id) throws ClassNotFoundException {
		String databaseURL = "jdbc:mysql://localhost:3306/classdb";
		String user = "root";
		String password = "";
		int id=0;
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String sql = "select max(roll_no) as rollno from student where class_id=" + class_id;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				id = result.getInt("rollno");
			}
			return id;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
