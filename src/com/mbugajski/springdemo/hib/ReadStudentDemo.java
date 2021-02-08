package com.mbugajski.springdemo.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mbugajski.springdemo.hib.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Student tempStudent1 = new Student("Peter", "Pan", "peterpan@gmail.com");
			Student tempStudent2 = new Student("Will", "Rens", "willrens@gmail.com");
			session.beginTransaction();
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student myStudent = new Student();
			myStudent = session.get(Student.class, tempStudent1.getId());
			System.out.println(myStudent);
			myStudent = session.get(Student.class, tempStudent2.getId());
			System.out.println(myStudent);
			session.getTransaction().commit();
			
			
		} finally {
			factory.close();
		}
	}

}
