package com.xenogears.cotizacion.repository;

import org.springframework.stereotype.Repository;

import com.xenogears.cotizacion.model.ConfigVariable;

@Repository
public interface ConfigVariableRepository extends GenericRepository<ConfigVariable, Integer>, ConfigVariableRepositoryCustom{

}
