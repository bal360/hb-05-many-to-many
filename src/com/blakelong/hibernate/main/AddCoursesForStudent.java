package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;
import com.blakelong.hibernate.entity.Review;
import com.blakelong.hibernate.entity.Student;

public class AddCoursesForStudent {

	public static void main(String[] args) {
		
		// create factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// begin transaction
			session.beginTransaction();
			
			// create id to get student and get student
			int id = 1;
			
			Student student = session.get(Student.class, id);
			
			// create courses
			Course course1 = new Course("MTH-2050: Calculus II");
			Course course2 = new Course("MTH-3273: Discrete Mathematics");
			
			// add student to courses
			course1.addStudent(student);
			course2.addStudent(student);
			
			// save courses
			session.save(course1);
			session.save(course2);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		} finally {
			session.close();
			
			factory.close();
		}
	}
}
