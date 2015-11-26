package com.xenogears.cotizacion.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.Auto;
import com.xenogears.cotizacion.model.Cliente;
import com.xenogears.cotizacion.model.ConfigVariable;
import com.xenogears.cotizacion.model.Cotizacion;
import com.xenogears.cotizacion.model.Vendedor;
import com.xenogears.cotizacion.service.AutoService;
import com.xenogears.cotizacion.service.ClienteService;
import com.xenogears.cotizacion.service.ConfigVariableService;
import com.xenogears.cotizacion.service.CotizacionService;
import com.xenogears.cotizacion.service.VendedorService;

@ManagedBean
@SessionScoped
public class AltaCotizacionManagedBean {
	
	@ManagedProperty("#{cotizacionService}")
	private CotizacionService cotizacionService;
	
	@ManagedProperty("#{autoService}")
	private AutoService autoService;
	
	@ManagedProperty("#{clienteService}")
	private ClienteService clienteService;
	
	@ManagedProperty("#{vendedorService}")
	private VendedorService vendedorService;
	
	@ManagedProperty("#{configVariableService}")
	private ConfigVariableService cofigVarService;
	
	private Cotizacion cotizacion;
	private List<Auto> autos = new ArrayList<Auto>();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Vendedor> vendedores = new ArrayList<Vendedor>();
	private List<ConfigVariable> tipoMonedas = new ArrayList<ConfigVariable>();
	private Auto autoSeleccionado = new Auto();
	private Vendedor vendedorSeleccionado = new Vendedor();
	private Cliente clienteSeleccionado = new Cliente();
	
	public String registrarCotizacion(){
		cotizacionService.getCotizacionRepository().save(cotizacion);
		return null;
	}
	
	public CotizacionService getCotizacionService() {
		return cotizacionService;
	}

	public void setCotizacionService(CotizacionService cotizacionService) {
		this.cotizacionService = cotizacionService;
	}

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}


	public ClienteService getClienteService() {
		return clienteService;
	}
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}


	public ConfigVariableService getCofigVarService() {
		return cofigVarService;
	}


	public void setCofigVarService(ConfigVariableService cofigVarService) {
		this.cofigVarService = cofigVarService;
	}

	public AutoService getAutoService() {
		return autoService;
	}

	public void setAutoService(AutoService autoService) {
		this.autoService = autoService;
	}

	public VendedorService getVendedorService() {
		return vendedorService;
	}

	public void setVendedorService(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

	//LISTAS
	public List<Auto> getAutos() {
		autos = autoService.obtenerAutos();
		return autos;
	}

	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}

	public List<Cliente> getClientes() {
		clientes = Lists.newArrayList(clienteService.getClienteRepository().obtenerPorEstado(true));
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Vendedor> getVendedores() {
		vendedores = Lists.newArrayList(vendedorService.getVendedorRepository().obtenerPorEstado(true));
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public List<ConfigVariable> getTipoMonedas() {
		tipoMonedas = cofigVarService.getConfigVarRepository().obtenerPorEstado(true);
		return tipoMonedas;
	}

	public void setTipoMonedas(List<ConfigVariable> tipoMonedas) {
		this.tipoMonedas = tipoMonedas;
	}

	public Auto getAutoSeleccionado() {
		return autoSeleccionado;
	}

	public void setAutoSeleccionado(Auto autoSeleccionado) {
		this.autoSeleccionado = autoSeleccionado;
	}

	public Vendedor getVendedorSeleccionado() {
		return vendedorSeleccionado;
	}

	public void setVendedorSeleccionado(Vendedor vendedorSeleccionado) {
		this.vendedorSeleccionado = vendedorSeleccionado;
	}

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}
	
	
}
