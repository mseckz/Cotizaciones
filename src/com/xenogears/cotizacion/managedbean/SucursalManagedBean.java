package com.xenogears.cotizacion.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.xenogears.cotizacion.model.Departamento;
import com.xenogears.cotizacion.model.Provincia;
import com.xenogears.cotizacion.model.Sucursal;
import com.xenogears.cotizacion.model.Ubigeo;
import com.xenogears.cotizacion.service.SucursalService;
import com.xenogears.cotizacion.service.UbigeoService;

@ManagedBean
@SessionScoped
public class SucursalManagedBean {
	
	private Sucursal sucursal = new Sucursal();
	private List<Sucursal> sucursales = new ArrayList<Sucursal>();
	private List<Departamento> departamentos = new ArrayList<Departamento>();
	private List<Provincia> provincias = new ArrayList<Provincia>();
	private List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
	
	
	@ManagedProperty("#{sucursalService}")
	private SucursalService sucursalService;
	
	@ManagedProperty("#{ubigeoService}")
	private UbigeoService ubigeoService;
	
	public String indexSucursal(){
		return "/paginas/mantenimiento/sucursal/sucursalIndex.xhtml?faces-redirect=true";
	}
	
	public String registrar(){
		sucursalService.registrar(sucursal);
		sucursal = new Sucursal();
		return null;
	}
	
	public void obtenerProvincias(){
		provincias = ubigeoService.obtenerProvincias(sucursal.getDepartamento().getCodigo());
	}
	
	public void obtenerDistritos(){
		ubigeos = ubigeoService.obtenerUbigeos(sucursal.getProvincia().getCodigo());
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

	public List<Departamento> getDepartamentos() {
		departamentos = ubigeoService.obtenerDepartamentos();
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public UbigeoService getUbigeoService() {
		return ubigeoService;
	}

	public void setUbigeoService(UbigeoService ubigeoService) {
		this.ubigeoService = ubigeoService;
	}

	public List<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public List<Ubigeo> getUbigeos() {
		return ubigeos;
	}

	public void setUbigeos(List<Ubigeo> ubigeos) {
		this.ubigeos = ubigeos;
	}
	
}	
