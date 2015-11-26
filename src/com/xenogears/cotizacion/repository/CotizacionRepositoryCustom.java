package com.xenogears.cotizacion.repository;

import java.util.List;

import com.xenogears.cotizacion.model.Cotizacion;

public interface CotizacionRepositoryCustom {
	
	List<Cotizacion> obtenerAprobados();
	
	List<Cotizacion> obtenerPendientes();
}
