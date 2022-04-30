package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Student;
import com.util.HibernateUtil;

@WebServlet("/read-student")
public class ReadStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadStudentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("<html><body><h1>Student list:-</h1>");
		out.println("<style> table,td,th { border:2px solid green; padding:10px; }</style>");

		out.println("<table>");

		out.println("<tr>");
		out.println("<th> Student Name </th>");
		out.println("<th> Student Class </th>");
		out.println("<th> Student Roll No </th>");
		out.println("<th> Student Address </th>");
		out.println("</tr>");
		
		String databaseURL = "jdbc:mysql://localhost:3306/classdb";
		String user = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String sql = "select a.name,a.roll_no,a.address,b.class_name from student a\r\n"
					+ "inner join classmaster b on b.class_id=a.class_id";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				
				out.println("<tr>");
				
				out.println("<td>" +result.getString("name") + "</td>");
				out.println("<td>" + result.getInt("roll_no") + "</td>");
				out.println("<td>" +result.getString("address") + "</td>");
				out.println("<td>" +result.getString("class_name") + "</td>");
				
				out.println("</tr>");
			}
		
		/*SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();

		List<Student> students = session.createQuery("from Student").list();
		if (students != null && students.size() > 0) {
			for (Student student : students) {
				out.println("<tr>");
				out.println("<td>" + student.getStudent_id() + "</td>");
				out.println("<td>" + student.getName() + "</td>");
				out.println("<td>" + student.getClass_id() + "</td>");
				out.println("<td>" + student.getRoll_no() + "</td>");
				out.println("<td>" + student.getAddress() + "</td>");
				
				out.println("</tr>");

			}
		}*/
		out.println("</table></body></html>");
		out.close();
	}
		catch(Exception e) {
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
