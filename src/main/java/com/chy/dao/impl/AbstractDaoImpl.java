package com.chy.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.chy.dao.AbstractDao;

public abstract class AbstractDaoImpl<E, I extends Serializable> implements
		AbstractDao<E, I> {
	private Class<E> entityClass;

	@Autowired
	private SessionFactory sessionFactory;

	protected AbstractDaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public E findById(I id) {
		return (E) this.getCurrentSession().get(this.entityClass, id);
	}

	public E saveOrUpdate(E e) {
		getCurrentSession().saveOrUpdate(e);
		return e;
	}

	public void delete(E e) {
		getCurrentSession().delete(e);
	}

	public List<E> findByCriteria(Criterion criterion) {
		Criteria criteria = getCurrentSession()
				.createCriteria(this.entityClass);
		criteria.add(criterion);
		return criteria.list();
	}

	public List<E> findAll() {
		String hql = "from " + this.entityClass.getName() + " as e";
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}
}