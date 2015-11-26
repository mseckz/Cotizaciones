package com.xenogears.cotizacion.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.ConfigVariable;
import com.xenogears.cotizacion.service.ConfigVariableService;

@ManagedBean
@SessionScoped
public class ConfigVariableManagedBean {

	private ConfigVariable configVar = new ConfigVariable();
	private List<ConfigVariable> variables;
	private List<ConfigVariable> variablesTabla;
	private boolean flagTabla;
	
	@ManagedProperty(value ="#{configVariableService}")
	private ConfigVariableService configvarService;

	public String indexVariables(){
		return "/paginas/mantenimiento/variables/indexVariables.xhtml?faces-redirect=true";
	}
	
	public String registrar(){
		if(flagTabla) configVar.setPadre(null);
		configvarService.getConfigVarRepository().save(configVar);
		configVar = new ConfigVariable();
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

	public List<ConfigVariable> getVariables() {
		variables = Lists.newArrayList(configvarService.getConfigVarRepository().findAll());
		return variables;
	}

	public void setVariables(List<ConfigVariable> variables) {
		this.variables = variables;
	}

	public List<ConfigVariable> getVariablesTabla() {
		variablesTabla = Lists.newArrayList(configvarService.getConfigVarRepository().listarTablas());
		return variablesTabla;
	}

	public void setVariablesTabla(List<ConfigVariable> variablesTabla) {
		this.variablesTabla = variablesTabla;
	}

	public boolean isFlagTabla() {
		return flagTabla;
	}

	public void setFlagTabla(boolean flagTabla) {
		this.flagTabla = flagTabla;
	}
	
}
