package com.luv2code.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
			
			//get the instructor detail object
			int theId= 5;
			InstructorDetail tempInstructorDetail=session.get(InstructorDetail.class,theId);
			//print the instructor detail
			System.out.println("tempInstructorDetail: " +tempInstructorDetail);
			//print the associated instructor
			System.out.println("the associated instrutor:" +tempInstructorDetail.getInstructor());	
			
			System.out.println("deleting the instructor detail "+tempInstructorDetail);
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch (Exception exc) {
			//  handle exception
		
			session.close();
		}
		finally {
			
			session.close();
			factory.close();
		}
	}

}





