package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;
import com.blakelong.hibernate.entity.Review;
import com.blakelong.hibernate.entity.Student;

public class CreateCourseAndStudentsDemo {
	
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
			
			// create course
			Course course = new Course("ENG-4303: Joy Williams");
			
			// save course
			session.save(course);
			
			// create students
			Student student1 = new Student("Blake", "Long", "bal@email.com");
			Student student2 = new Student("Wes", "Kenneth", "freshwes@email.com");
			Student student3 = new Student("john", "Andrews", "longgone@email.com");
			Student student4 = new Student("john", "doe", "doughface@email.com");
			
			// add students to course
			course.addStudent(student1);
			course.addStudent(student2);
			course.addStudent(student3);
			course.addStudent(student4);
			
			// save students
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.save(student4);
			
			for (Student student : course.getStudents()) {
				System.out.println("Student: " + student);
			}
			
			// commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			
			factory.close();
		}
	}
}
