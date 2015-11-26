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
				+ "Select c from Cotizacion c where c.flagAprobado = false and c.flagEstado = true", Cotizacion.class);
		return query.getResultList();
	}

}
