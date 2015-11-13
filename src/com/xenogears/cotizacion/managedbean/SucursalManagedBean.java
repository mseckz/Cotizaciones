package com.xenogears.cotizacion.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.xenogears.cotizacion.model.Sucursal;
import com.xenogears.cotizacion.service.SucursalService;

@ManagedBean
@SessionScoped
public class SucursalManagedBean {
	
	private Sucursal sucursal = new Sucursal();
	private List<Sucursal> sucursales = new ArrayList<Sucursal>();
	
	@ManagedProperty("#{sucursalService}")
	private SucursalService sucursalService;
	
	public String indexSucursal(){
		return "/paginas/mantenimiento/sucursal/sucursalIndex.xhtml?faces-redirect=true";
	}
	
	public String registrar(){
		sucursalService.registrar(sucursal);
		sucursal = new Sucursal();
		return null;
	}

	public SucursalService getSucursalService() {
		return sucursalService;
	}

	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public List<Sucursal> getSucursales() {
		sucursales = sucursalService.listarSucursales();
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	
}
