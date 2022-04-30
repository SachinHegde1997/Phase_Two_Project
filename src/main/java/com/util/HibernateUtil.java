package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.ClassDetails;
import com.entity.ClassMaster;
import com.entity.Student;
import com.entity.Subject;
import com.entity.TeacherMaster;

public class HibernateUtil {
	static SessionFactory sessionFactory;

	public static SessionFactory buildSessionFactory() {

		if (sessionFactory == null) {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			cfg.addAnnotatedClass(Student.class);
			cfg.addAnnotatedClass(Subject.class);
			cfg.addAnnotatedClass(ClassMaster.class);
			cfg.addAnnotatedClass(TeacherMaster.class);
			cfg.addAnnotatedClass(ClassDetails.class);
			
			sessionFactory = cfg.buildSessionFactory();
		}
		return sessionFactory;
	}
}
