package com.mbugajski.springdemo.hib;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mbugajski.springdemo.hib.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			int studentId = 1;
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Getting student with id=" + studentId);
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Get complete: " + myStudent);
			
			System.out.println("Updating student.");
			myStudent.setFirstName("Adam");
			System.out.println("Update complete: " + myStudent);
			
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			displayStudents(theStudents);
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			theStudents = session.createQuery("from Student").getResultList();
			
			System.out.println("\nSession hasn't been commited yet so db queary will show no changes:");
			displayStudents(theStudents);
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			theStudents = session.createQuery("from Student").getResultList();
			
			System.out.println("\nNew query after the commit shows that records have been updated:");
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
		System.out.println("\n");
	}

}
