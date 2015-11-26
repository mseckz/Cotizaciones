package com.xenogears.cotizacion.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.xenogears.cotizacion.repository.AutoRepositoryCustom;

public class AutoRepositoryImpl implements AutoRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String obtenerCodigo() {
		String hql = "SELECT a.codigoAuto from Auto a order by a.idAuto desc";
		TypedQuery<String> query = entityManager.createQuery(hql, String.class);
		query.setMaxResults(1);
		List<String> resultado = query.getResultList();
		return resultado.isEmpty() ? "A000001" : resultado.get(0);
	}

}
