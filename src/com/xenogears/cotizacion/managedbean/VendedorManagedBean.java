package com.xenogears.cotizacion.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.Sucursal;
import com.xenogears.cotizacion.model.Vendedor;
import com.xenogears.cotizacion.service.SucursalService;
import com.xenogears.cotizacion.service.VendedorService;

@ManagedBean
@SessionScoped
public class VendedorManagedBean {
	
	private Vendedor vendedor;
	private List<Sucursal> sucursales;
	private List<Vendedor> vendedores;

	@ManagedProperty(value="#{vendedorService}")
	VendedorService servicio;
	
	@ManagedProperty(value="#{sucursalService}")
	SucursalService sucursalService;
	
	@PostConstruct
	public void init(){
		vendedor = new Vendedor();
		sucursales = sucursalService.listarSucursales();
		vendedores = new ArrayList<Vendedor>();
	}
	
	public String indexVendedor(){
		return "/paginas/mantenimiento/vendedor/vendedorIndex.xhtml?faces-redirect=true";
	}
	
	public String grabar(){
		servicio.getVendedorRepository().save(vendedor);
		vendedor = new Vendedor();
		return null;
	}
	
	public String cargarVendedor(Integer idVendedor){
		vendedor = servicio.getVendedorRepository().findOne(idVendedor);
		return null;
	}
	
	public String eliminarVendedor(Integer idVendedor){
		Vendedor vend = servicio.getVendedorRepository().findOne(idVendedor);
		vend.setFlagEstado(false);
		servicio.getVendedorRepository().save(vend);
		vendedores = Lists.newArrayList(servicio.getVendedorRepository().obtenerPorEstado(true));
		return null;
	}
	
	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	
	public List<Vendedor> getVendedores() {
		vendedores = Lists.newArrayList(servicio.getVendedorRepository().obtenerPorEstado(true));
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public VendedorService getServicio() {
		return servicio;
	}

	public void setServicio(VendedorService servicio) {
		this.servicio = servicio;
	}

	public SucursalService getSucursalService() {
		return sucursalService;
	}

	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
	
}
