package com.xenogears.cotizacion.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.xenogears.cotizacion.repository.VendedorRepositoryCustom;

public class VendedorRepositoryImpl implements VendedorRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String obtenerCodigo() {
		String hql = "SELECT v.codigoVendedor from Vendedor v order by v.idVendedor desc";
		TypedQuery<String> query = entityManager.createQuery(hql, String.class);
		query.setMaxResults(1);
		List<String> resultado = query.getResultList();
		return resultado.isEmpty() ? "V00000" : resultado.get(0);
	}

}
