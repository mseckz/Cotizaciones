package com.xenogears.cotizacion.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.Cliente;
import com.xenogears.cotizacion.model.ConfigVariable;
import com.xenogears.cotizacion.model.Usuario;
import com.xenogears.cotizacion.service.ClienteService;
import com.xenogears.cotizacion.service.ConfigVariableService;
import com.xenogears.cotizacion.service.UsuarioService;

@ManagedBean
@SessionScoped
public class ClienteManagedBean {
	private Usuario usuario;
	private Cliente cliente;
	private List<Cliente> clientes;
	private List<ConfigVariable> variable;

	@ManagedProperty(value="#{clienteService}")
	ClienteService servicio;
	
	@ManagedProperty(value="#{configVariableService}")
	ConfigVariableService variableService;
	
	@ManagedProperty(value="#{usuarioService}")
	UsuarioService usuarioService;
	
	@PostConstruct
	public void init(){
		cliente = new Cliente();
		usuario = new Usuario();
		variable = new ArrayList<ConfigVariable>();
		clientes=new ArrayList<Cliente>();
	}
	
	public String indexCliente(){
		return "/paginas/mantenimiento/cliente/clienteIndex.xhtml?faces-redirect=true";
	}
	
	public String grabar(){
		Cliente cli = servicio.getClienteRepository().save(cliente);
		servicio.getClienteRepository().save(cli);
		return null;
	}
	
	public String cargarVendedor(Integer idCliente){
		cliente = servicio.getClienteRepository().findOne(idCliente);
		return null;
	}
	
	public String eliminarCliente(Integer idCliente){
		Cliente cli = servicio.getClienteRepository().findOne(idCliente);
		cli.setFlagEstado(false);
		servicio.getClienteRepository().save(cli);
		setClientes(Lists.newArrayList(servicio.getClienteRepository().obtenerPorEstado(true)));
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ConfigVariable> getVariable() {
		return variable;
	}

	public void setVariable(List<ConfigVariable> variable) {
		this.variable = variable;
	}

	public ClienteService getServicio() {
		return servicio;
	}

	public void setServicio(ClienteService servicio) {
		this.servicio = servicio;
	}

	public ConfigVariableService getVariableService() {
		return variableService;
	}

	public void setVariableService(ConfigVariableService variableService) {
		this.variableService = variableService;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	

	
}
