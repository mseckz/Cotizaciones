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
	private Cliente cliente;
	private List<Cliente> clientes;
	private ConfigVariable variable;
	private List<ConfigVariable> variables;

	@ManagedProperty(value="#{clienteService}")
	ClienteService servicio;
	
	@ManagedProperty(value="#{configVariableService}")
	ConfigVariableService variableService;
	
	
	@PostConstruct
	public void init(){
		cliente = new Cliente();
		variables = new ArrayList<ConfigVariable>();
		clientes=new ArrayList<Cliente>();
	}
	
	public String indexCliente(){
		return "/paginas/mantenimiento/cliente/clienteIndex.xhtml?faces-redirect=true";
	}
	
	public String grabar(){
		Cliente cli = servicio.getClienteRepository().save(cliente);
		ConfigVariable var=variableService.getConfigVarRepository().save(variable);
		System.out.println("objeto cli" + cli.getNombres());
		System.out.println("objeto cliente" + cliente.getNombres());
		String desTipoDoc,desTipoCli;
		desTipoCli=variableService.getConfigVarRepository().obtenerDescripcion(var.getIdConfigVariable());
		System.out.println("des sdadasdasd "+ desTipoCli);
		cli.setIdCliente(1);
		cli.setIdTipoCliente(1);
		cli.setIdTipoDocumento(1);
		servicio.getClienteRepository().save(cli);
		cliente = new Cliente();
		return null;
	}




	public ConfigVariable getVariable() {
		return variable;
	}

	public void setVariable(ConfigVariable variable) {
		this.variable = variable;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ConfigVariable> getVariables() {
		variables=Lists.newArrayList(variableService.getConfigVarRepository().obtenerPorid(11));
		return variables;
	}

	public void setVariables(List<ConfigVariable> variables) {
		this.variables = variables;
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

	
	
	public List<Cliente> getClientes() {
		clientes=Lists.newArrayList(servicio.getClienteRepository().obtenerPorEstado(true));
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	

	
}
