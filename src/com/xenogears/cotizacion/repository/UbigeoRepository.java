package com.xenogears.cotizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xenogears.cotizacion.model.Ubigeo;

@Repository
public interface UbigeoRepository extends GenericRepository<Ubigeo, Integer> {
	
	@Query("Select u from Ubigeo u where SUBSTRING(u.codigo, 1,4) = ?1")
	List<Ubigeo> obtenerUbigeos(String codigoProv);
}
