package com.xenogears.cotizacion.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.xenogears.cotizacion.repository.ClienteRepositoryCustom;

public class ClienteRepositoryImpl implements ClienteRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String obtenerCodigo() {
		String hql = "SELECT c.codigoCliente from Cliente c order by c.idCliente desc";
		TypedQuery<String> query = entityManager.createQuery(hql, String.class);
		query.setMaxResults(1);
		List<String> resultado = query.getResultList();
		return resultado.isEmpty() ? "CL0000" : resultado.get(0);
	}
	
	
}
