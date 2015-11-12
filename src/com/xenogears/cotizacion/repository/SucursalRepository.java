package com.xenogears.cotizacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenogears.cotizacion.model.Sucursal;

@Repository
public interface SucursalRepository extends CrudRepository<Sucursal, Integer> {

}
