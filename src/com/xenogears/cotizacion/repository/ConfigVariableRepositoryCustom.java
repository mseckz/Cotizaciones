package com.xenogears.cotizacion.repository;

import java.util.List;

import com.xenogears.cotizacion.model.ConfigVariable;

public interface ConfigVariableRepositoryCustom {
	
	List<ConfigVariable> listarTablas();
	
	List<ConfigVariable> obtenerPorid(Integer id);
}
