package com.xenogears.cotizacion.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.xenogears.cotizacion.repository.GenericRepository;


public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, Serializable> implements GenericRepository<T, Serializable>{

	private final EntityManager entityManager;
	protected Class<T> clazz;
	
	public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
		this.clazz = domainClass;
	}

	@Override
	public List<T> obtenerPorEstado(boolean estado) {
		String hql = "Select t from " + clazz.getSimpleName() + " t where t.flagEstado=:estado";
		TypedQuery<T> query = entityManager.createQuery(hql, clazz);
		query.setParameter("estado", estado);
		return query.getResultList();
	}

}
