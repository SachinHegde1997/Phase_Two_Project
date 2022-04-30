package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/read-cls")
public class ReadClassDetails extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadClassDetails() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("<html><body><h1>Class Details:-</h1>");
		out.println("<style> table,td,th { border:2px solid green; padding:10px; }</style>");

		out.println("<table>");

		out.println("<tr>");
		out.println("<th> Class </th>");
		out.println("<th> Teacher(Subject) </th>");
		out.println("<th> Students</th>");
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
			String sql = "select b.class_name,\r\n"
					+ "GROUP_CONCAT(distinct CONCAT(c.teacher_name, \"( \", d.subject_name, \")\")) as tchdt, \r\n"
					+ "GROUP_CONCAT(distinct e.name) as student \r\n"
					+ "from class_details a\r\n"
					+ "inner join classmaster b on b.class_id=a.class_id\r\n"
					+ "inner join teachermaster c on c.teacher_id=a.teacher_id\r\n"
					+ "inner join subject d on d.subject_id=a.subject_id\r\n"
					+ "inner join student e on e.class_id=a.class_id\r\n"
					+ "group by class_name;";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				
				out.println("<tr>");
				
				out.println("<td>" +result.getString("class_name") + "</td>");
				out.println("<td>" + result.getString("tchdt") + "</td>");
				out.println("<td>" +result.getString("student") + "</td>");
				
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
