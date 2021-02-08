package com.mbugajski.springdemo.hib;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mbugajski.springdemo.hib.entity.Student;

public class DeleteStudentDemo {

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
			
			System.out.println("Deleting student after getting it:");
			session.delete(myStudent);
			
			session.getTransaction().commit();
			
			session.beginTransaction();
			
			System.out.println("Deleting student via query:");
			session.createQuery("delete from Student where id='2'").executeUpdate();
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}
	
}
