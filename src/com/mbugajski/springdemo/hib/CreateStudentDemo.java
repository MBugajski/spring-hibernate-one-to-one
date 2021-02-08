package com.mbugajski.springdemo.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mbugajski.springdemo.hib.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Student tempStudent = new Student("Adam", "Smith", "adamsmith@gmail.com");
			session.beginTransaction();
			session.save(tempStudent);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
