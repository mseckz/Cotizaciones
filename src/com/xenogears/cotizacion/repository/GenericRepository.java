package com.xenogears.cotizacion.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository <T, ID extends Serializable> extends CrudRepository<T, Serializable>{
	
	List<T> obtenerPorEstado(boolean estado);
	
}
