package com.xenogears.cotizacion.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.xenogears.cotizacion.model.ConfigVariable;
import com.xenogears.cotizacion.service.ConfigVariableService;

@ManagedBean
@SessionScoped
public class ConfigVaribleManagedBean {

	private ConfigVariable configVar;
	
	@ManagedProperty(value ="#{configVariableService}")
	private ConfigVariableService configvarService;

	public String registrar(){
		configvarService.getConfigVarRepository().save(configVar);
		return null;
	}
	
	
	public ConfigVariableService getConfigvarService() {
		return configvarService;
	}

	public void setConfigvarService(ConfigVariableService configvarService) {
		this.configvarService = configvarService;
	}

	public ConfigVariable getConfigVar() {
		return configVar;
	}

	public void setConfigVar(ConfigVariable configVar) {
		this.configVar = configVar;
	}
	
}
