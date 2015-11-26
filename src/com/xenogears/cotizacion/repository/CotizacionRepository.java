package com.xenogears.cotizacion.repository;

import org.springframework.stereotype.Repository;

import com.xenogears.cotizacion.model.Cotizacion;

@Repository
public interface CotizacionRepository extends GenericRepository<Cotizacion, Integer>, CotizacionRepositoryCustom {

}
