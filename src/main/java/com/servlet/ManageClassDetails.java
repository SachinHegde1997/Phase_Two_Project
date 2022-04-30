package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.ClassDao;
import com.dao.SubjectDao;
import com.dao.TeacherDao;
import com.entity.ClassDetails;
import com.entity.ClassMaster;
import com.entity.Student;
import com.entity.Subject;
import com.entity.TeacherMaster;
import com.util.HibernateUtil;

@WebServlet("/manage-class")
public class ManageClassDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ManageClassDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		ClassDao dao = new ClassDao();
		TeacherDao tdao=new TeacherDao();
		SubjectDao sdao=new SubjectDao();
		try {

			List<ClassMaster> classCatagory = dao.list();
			List<Subject> subsCatagory = sdao.list();
			List<TeacherMaster> tchCatagory = tdao.list();
			request.setAttribute("clsCategory", classCatagory);
			request.setAttribute("subsCategory", subsCatagory);
			request.setAttribute("tchCatagory", tchCatagory);
			request.getRequestDispatcher("addclassdet.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

		}
		//request.getRequestDispatcher("addclassdet.jsp").forward(request, response);
		// System.out.println("hhhh");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ClassDetails clsd = new ClassDetails();

		// Populate Student object
		populateCls(request, clsd);

		// Step 2: Initialize hibernate to psersist object
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		session.save(clsd);
		tx.commit();

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("Data successfully inserted in DB");
		out.println("<p> <a href='read-cls'> Click Here </a> to show Class Details</p>");
		out.println("</body></html>");
	}

	private void populateCls(HttpServletRequest request, ClassDetails clsd) {
		try {
			int class_id = Integer.parseInt(request.getParameter("class_id")); 
			int sid = Integer.parseInt(request.getParameter("sid")); 
			int tid = Integer.parseInt(request.getParameter("tid")); 
			
			clsd.setClass_id(class_id);
			clsd.setSubject_id(sid);
			clsd.setTeacher_id(tid);
		} catch (Exception e) {

		}

	}
}
