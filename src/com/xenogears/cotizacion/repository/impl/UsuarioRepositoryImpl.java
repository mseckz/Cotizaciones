package com.xenogears.cotizacion.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.xenogears.cotizacion.model.Usuario;
import com.xenogears.cotizacion.repository.UsuarioRepositoryCustom;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Usuario validarLogin(String correo, String password) {
		TypedQuery<Usuario> query = entityManager.createQuery(""
				+ "Select u from Usuario u where u.correo =:correo and u.clave=:password and u.flagEstado = true", Usuario.class);
		query.setParameter("correo", correo);
		query.setParameter("password", password);
		List<Usuario> resultado = query.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}

	@Override
	public Usuario obtenerUsuario(String correo) {
		TypedQuery<Usuario> query = entityManager.createQuery(""
				+ "Select u from Usuario u where u.correo =:correo and u.flagEstado = true", Usuario.class);
		query.setParameter("correo", correo);
		List<Usuario> resultado = query.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}

}
