package com.greatlearning.smsapp_demo.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.smsapp_demo.entity.Student;
@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}


	}
	@Transactional
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		// find all the records from the database table
		List<Student> students=session.createQuery("from Student").list();
		tx.commit();
		return students;

	}

	@Transactional
	public Student findbyId(int id) {
		// TODO Auto-generated method stub
		Student student = new Student();
		Transaction tx = session.beginTransaction();
		student = session.get(Student.class, id);
		tx.commit();
		return student;
		}

	@Transactional
	public void save(Student theStudent) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(theStudent);
		tx.commit();
	}

	@Transactional
	public void deletebyId(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		// get transaction
		Student student = session.get(Student.class, id);
		// delete record
		session.delete(student);
		tx.commit();
	}

}
