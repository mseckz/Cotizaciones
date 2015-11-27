package com.xenogears.cotizacion.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.xenogears.cotizacion.model.Auto;
import com.xenogears.cotizacion.model.ConfigVariable;

@ManagedBean
@SessionScoped
public class AutoManagedBean {
	private Auto auto;
	private List<Auto> autos;
	private List<ConfigVariable> configVariables;
	
	
}
