package com.xenogears.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xenogears.cotizacion.model.Auto;
import com.xenogears.cotizacion.repository.AutoRepository;

@Component
public class AutoService {
	
	@Autowired
	AutoRepository autoRepository;
	
	public List<Auto> obtenerAutos(){
		return autoRepository.obtenerPorEstado(true);
	}
	
	public void grabarAuto(Auto auto){
		autoRepository.save(auto);
	}

	public AutoRepository getAutoRepository() {
		return autoRepository;
	}

	public void setAutoRepository(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}
	
}
