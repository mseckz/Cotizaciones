package com.xenogears.cotizacion.repository;

import org.springframework.stereotype.Repository;

import com.xenogears.cotizacion.model.Usuario;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, Integer>, UsuarioRepositoryCustom {
	
}
