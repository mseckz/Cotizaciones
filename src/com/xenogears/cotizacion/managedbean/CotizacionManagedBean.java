package com.xenogears.cotizacion.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

import com.xenogears.cotizacion.model.Cotizacion;
import com.xenogears.cotizacion.service.CotizacionService;

@ManagedBean
@SessionScoped
public class CotizacionManagedBean {

	private Cotizacion cotizacion;
	private List<Cotizacion> cotizaciones = new ArrayList<Cotizacion>();

	@ManagedProperty("#{cotizacionService}")
	CotizacionService cotService;

	@PostConstruct
	public void init() {
		cotizaciones = cotService.getCotizacionRepository().obtenerPendientes();
	}

	public String redireccionarIndex() {
		cotizaciones = cotService.getCotizacionRepository().obtenerPendientes();
		return "/paginas/cotizacion/indexCotizacion.xhtml?faces-redirect=true";
	}

	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getId().equals("pendientes")) {
			cotizaciones = cotService.getCotizacionRepository()
					.obtenerPendientes();
		}
		if (event.getTab().getId().equals("aprobados")) {
			cotizaciones = cotService.getCotizacionRepository()
					.obtenerAprobados();
		}
		if (event.getTab().getId().equals("anulados")) {
			cotizaciones = cotService.getCotizacionRepository()
					.obtenerAnulados();
		}
	}

	public void cargarCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
		RequestContext.getCurrentInstance().execute(
				"PF('w_detalleCotizacionDialog').show();");
	}

	public void aprobarCotizacion() {
		cotizacion.setFlagAprobado(true);
		cotService.getCotizacionRepository().save(cotizacion);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
				"Cotizacion " + cotizacion.getCodigoCotizacion() + " aprobada");
		FacesContext.getCurrentInstance().addMessage(null, message);
		cotizaciones = cotService.getCotizacionRepository().obtenerPendientes();
	}

	public void anularCotizacion() {
		cotizacion.setFlagAnulado(true);
		cotService.getCotizacionRepository().save(cotizacion);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
				"Cotizacion " + cotizacion.getCodigoCotizacion() + " anulada");
		FacesContext.getCurrentInstance().addMessage(null, message);
		cotizaciones = cotService.getCotizacionRepository().obtenerPendientes();
	}

	public CotizacionService getCotService() {
		return cotService;
	}

	public void setCotService(CotizacionService cotService) {
		this.cotService = cotService;
	}

	public List<Cotizacion> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(List<Cotizacion> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

}
