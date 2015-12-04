package com.xenogears.cotizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.xenogears.cotizacion.model.Provincia;

public interface ProvinciaRepository extends GenericRepository<Provincia, Integer>{
	
	
	@Query("Select p from Provincia p where SUBSTRING(p.codigo, 1, 2) = ?1")
	public List<Provincia> obtenerProvincias(String codigoDept);
}
