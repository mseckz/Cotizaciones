package com.xenogears.cotizacion.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.xenogears.cotizacion.model.Cotizacion;
import com.xenogears.cotizacion.service.CotizacionService;

@ManagedBean
@SessionScoped
public class CotizacionManagedBean {
	
	@ManagedProperty("#{cotizacionService}") 
	CotizacionService cotService;

	private List<Cotizacion> cotizacionesPendientes = new ArrayList<Cotizacion>();
	private List<Cotizacion> cotizacionesAprobadas = new ArrayList<Cotizacion>();
	
	
	public String redireccionarIndex(){
		return "/paginas/cotizacion/indexCotizacion.xhtml?faces-redirect=true";
	}
	
	
	public CotizacionService getCotService() {
		return cotService;
	}

	public void setCotService(CotizacionService cotService) {
		this.cotService = cotService;
	}

	public List<Cotizacion> getCotizacionesPendientes() {
		cotizacionesPendientes = cotService.getCotizacionRepository().obtenerPendientes();
		return cotizacionesPendientes;
	}

	public void setCotizacionesPendientes(List<Cotizacion> cotizacionesPendientes) {
		this.cotizacionesPendientes = cotizacionesPendientes;
	}

	public List<Cotizacion> getCotizacionesAprobadas() {
		cotizacionesAprobadas = cotService.getCotizacionRepository().obtenerAprobados();
		return cotizacionesAprobadas;
	}

	public void setCotizacionesAprobadas(List<Cotizacion> cotizacionesAprobadas) {
		this.cotizacionesAprobadas = cotizacionesAprobadas;
	}
	
}
