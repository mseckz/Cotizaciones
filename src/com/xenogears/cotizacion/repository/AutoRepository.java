package com.xenogears.cotizacion.repository;

import org.springframework.stereotype.Repository;

import com.xenogears.cotizacion.model.Auto;

@Repository
public interface AutoRepository extends GenericRepository<Auto, Integer>, AutoRepositoryCustom{

}
