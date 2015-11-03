package com.xenogears.cotizacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenogears.cotizacion.model.ConfigVariable;

@Repository
public interface ConfigVariableRepository extends CrudRepository<ConfigVariable, Integer>{

}
