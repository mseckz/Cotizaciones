package com.xenogears.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.Departamento;
import com.xenogears.cotizacion.model.Provincia;
import com.xenogears.cotizacion.model.Ubigeo;
import com.xenogears.cotizacion.repository.DepartamentoRepository;
import com.xenogears.cotizacion.repository.ProvinciaRepository;
import com.xenogears.cotizacion.repository.UbigeoRepository;

@Component
public class UbigeoService {
	
	@Autowired
	private DepartamentoRepository deptRepository;
	
	@Autowired
	private ProvinciaRepository provRepository;
	
	@Autowired
	private UbigeoRepository ubigeoRepository;
	
	public List<Departamento> obtenerDepartamentos(){
		return Lists.newArrayList(deptRepository.findAll());
	}
	
	public List<Provincia> obtenerProvincias(String codigoDept){
		return provRepository.obtenerProvincias(codigoDept);
	}
	
	public List<Ubigeo> obtenerUbigeos(String codigoProv){
		return ubigeoRepository.obtenerUbigeos(codigoProv);
	}
	

	public DepartamentoRepository getDeptRepository() {
		return deptRepository;
	}

	public void setDeptRepository(DepartamentoRepository deptRepository) {
		this.deptRepository = deptRepository;
	}

	public ProvinciaRepository getProvRepository() {
		return provRepository;
	}

	public void setProvRepository(ProvinciaRepository provRepository) {
		this.provRepository = provRepository;
	}

	public UbigeoRepository getUbigeoRepository() {
		return ubigeoRepository;
	}

	public void setUbigeoRepository(UbigeoRepository ubigeoRepository) {
		this.ubigeoRepository = ubigeoRepository;
	}
	
	
}
