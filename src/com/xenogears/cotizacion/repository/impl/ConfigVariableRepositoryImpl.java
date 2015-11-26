package com.xenogears.cotizacion.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.xenogears.cotizacion.model.ConfigVariable;
import com.xenogears.cotizacion.repository.ConfigVariableRepositoryCustom;

public class ConfigVariableRepositoryImpl implements ConfigVariableRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ConfigVariable> listarTablas() {
		TypedQuery<ConfigVariable> query = entityManager.createQuery(""
				+ "Select c from ConfigVariable c where c.padre.idConfigVariable = null and"
				+ " c.flagEstado = true", ConfigVariable.class);
		return query.getResultList();
	}

	@Override
	public List<ConfigVariable> obtenerPorid(Integer id) {
		System.out.println(id);
		TypedQuery<ConfigVariable> query = entityManager.createQuery(""
				+ "Select c from ConfigVariable c where c.padre.idConfigVariable = :id and"
				+ " c.flagEstado = true", ConfigVariable.class);
			query.setParameter("id", id);

		return query.getResultList();
	}

}
