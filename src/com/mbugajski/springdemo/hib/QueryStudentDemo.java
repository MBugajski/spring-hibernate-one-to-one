package com.mbugajski.springdemo.hib;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mbugajski.springdemo.hib.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			
			
			theStudents = session.createQuery("from Student s where s.lastName='Pan'").getResultList();
			
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.lastName='Pan' OR s.firstName='Will'").getResultList();
			
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.email LIKE '%@gmail.com'").getResultList();

			displayStudents(theStudents);
			
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
