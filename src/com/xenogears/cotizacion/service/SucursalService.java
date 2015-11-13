package com.xenogears.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.Sucursal;
import com.xenogears.cotizacion.repository.SucursalRepository;

@Component
public class SucursalService {
	
	@Autowired
	private SucursalRepository sucursalRepository;
	
	public List<Sucursal> listarSucursales(){
		return Lists.newArrayList(sucursalRepository.findAll());
	}
	
	public Sucursal registrar(Sucursal sucursal){
		return sucursalRepository.save(sucursal);
	}
}
