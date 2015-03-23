package com.cricpoker.data.access;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cricpoker.util.HibernateUtil;

public class CommonDao<T> {
	
	private Class<T> type;
	
	@SuppressWarnings("hiding")
	public <T> CommonDao(Class clazz) {
		type = clazz;
	}

	@SuppressWarnings("hiding")
	public <T> T insert(T object) {
		
		Transaction transaction = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			System.out.println(e);
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		
		return object;
		
	}
	
	public <T> List<T> queryByCriteria(String columnName, String columnValue) {
		
		Transaction transaction = null;
		Session session = null;
		List<T> results = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(type);
			criteria.add(Restrictions.like(columnName, columnValue));
			
			results = criteria.list();
			
			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e);
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		
		return results;
	}
	
	public <T> List<T> queryAll() {
		Transaction transaction = null;
		Session session = null;
		List<T> results = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			results = session.createCriteria(type).list();
			
			session.getTransaction().commit();
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		
		return results;
	}
	
}
