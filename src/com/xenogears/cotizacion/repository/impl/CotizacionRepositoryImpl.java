package com.xenogears.cotizacion.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.xenogears.cotizacion.model.Cotizacion;
import com.xenogears.cotizacion.repository.CotizacionRepositoryCustom;

public class CotizacionRepositoryImpl implements CotizacionRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cotizacion> obtenerAprobados() {
		TypedQuery<Cotizacion> query = entityManager.createQuery(""
				+ "Select c from Cotizacion c where c.flagAprobado = true and c.flagEstado = true", Cotizacion.class);
		return query.getResultList();
	}

	@Override
	public List<Cotizacion> obtenerPendientes() {
		TypedQuery<Cotizacion> query = entityManager.createQuery(""
				+ "Select c from Cotizacion c where c.flagAprobado = false and c.flagAnulado = false and c.flagEstado = true", Cotizacion.class);
		return query.getResultList();
	}

	@Override
	public String obtenerCodigo() {
		String hql = "SELECT a.codigoCotizacion from Cotizacion a order by a.idCotizacion desc";
		TypedQuery<String> query = entityManager.createQuery(hql, String.class);
		query.setMaxResults(1);
		List<String> resultado = query.getResultList();
		return resultado.isEmpty() ? "COT001" : resultado.get(0);
	}

	@Override
	public List<Cotizacion> obtenerAnulados() {
		TypedQuery<Cotizacion> query = entityManager.createQuery(""
				+ "Select c from Cotizacion c where c.flagAnulado = true and c.flagEstado = true", Cotizacion.class);
		return query.getResultList();
	}

}
