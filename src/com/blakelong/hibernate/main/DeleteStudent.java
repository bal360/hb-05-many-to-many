package com.blakelong.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;
import com.blakelong.hibernate.entity.Review;
import com.blakelong.hibernate.entity.Student;

public class DeleteStudent {
	
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
			
			// create id and get student
			int id = 4;
			Student student = session.get(Student.class, id);
			
			// print out courses for student
			List<Course> courses = student.getCourses();
			System.out.println("\n");
			for (Course course : courses) {
				System.out.println("Course: " + course.getTitle());
			}
			System.out.println("\n");
			
			// delete student
			session.delete(student);
			
			// commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			
			factory.close();
		}
	}
}
