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

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.Cliente;
import com.xenogears.cotizacion.model.ConfigVariable;
import com.xenogears.cotizacion.service.ClienteService;
import com.xenogears.cotizacion.service.ConfigVariableService;
import com.xenogears.cotizacion.util.Constantes;

@ManagedBean
@SessionScoped
public class ClienteManagedBean {
	
	private Cliente cliente;
	private List<Cliente> clientes;
	private ConfigVariable variable;
	private List<ConfigVariable> variables;
	private List<ConfigVariable> tipoClientes;
	private Cliente clienteEliminar;
	
	private Integer tamanoNumeroDocumento;


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
		if(cliente.getIdCliente() == null){
			//Obtener codigo
			String ultimoCodigo = servicio.getClienteRepository().obtenerCodigo();
			Integer numeroCodigo = Integer.parseInt(ultimoCodigo.substring(3));
			String nuevoCodigo = "CL" + String.format("%04d", (numeroCodigo+1));
			cliente.setCodigoCliente(nuevoCodigo);
			
			//Obtener tipos
			ConfigVariable var = variableService.getConfigVarRepository().findOne(cliente.getIdTipoDocumento());
			cliente.setDescripTipoDoc(var.getDescripcion());
			ConfigVariable tipoCli = variableService.getConfigVarRepository().findOne(cliente.getIdTipoCliente());
			cliente.setDescripTipoCliente(tipoCli.getDescripcion());
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cliente "+ cliente.getCodigoCliente() +" registrado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		else{
			ConfigVariable var = variableService.getConfigVarRepository().findOne(cliente.getIdTipoDocumento());
			cliente.setDescripTipoDoc(var.getDescripcion());
			ConfigVariable tipoCli = variableService.getConfigVarRepository().findOne(cliente.getIdTipoCliente());
			cliente.setDescripTipoCliente(tipoCli.getDescripcion());
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cliente modificado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		servicio.getClienteRepository().save(cliente);
		cliente = new Cliente();
		
		return null;
	}

	public void cargarCliente(Cliente cliente){
		this.cliente = cliente;
		onTipoDocChange();
	}
	
	public void cargarClienteEliminar(Cliente cliente){
		this.clienteEliminar = cliente;
	}
	
	public void eliminarCliente(){
		clienteEliminar.setFlagEstado(false);
		servicio.getClienteRepository().save(clienteEliminar);
		clienteEliminar = new Cliente();
		RequestContext.getCurrentInstance().execute("{setTimeout(function() {PF('w_eliminarClienteDialog').hide();},1000);}");
//		RequestContext.getCurrentInstance().execute("{setTimeout(function() {PF('w_tablaUsuarios').clearFilters();},1000);}");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cliente eliminado con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void limpiarForm(){
		cliente = new Cliente();
	}
	
	public void onTipoDocChange(){
		Integer idTipoDoc = cliente.getIdTipoDocumento();
		switch(idTipoDoc){
		case 12:
			tamanoNumeroDocumento = 8;break;
		case 13:
			tamanoNumeroDocumento = 11;break;
		}
		System.out.println(tamanoNumeroDocumento);
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
		variables=Lists.newArrayList(variableService.getConfigVarRepository().obtenerPorid(Constantes.ID_TABLA_TIPO_DOCUMENTO));
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

	public List<ConfigVariable> getTipoClientes() {
		tipoClientes = Lists.newArrayList(variableService.getConfigVarRepository().obtenerPorid(Constantes.ID_TABLA_TIPO_CLIENTE));
		return tipoClientes;
	}

	public void setTipoClientes(List<ConfigVariable> tipoClientes) {
		this.tipoClientes = tipoClientes;
	}

	public Integer getTamanoNumeroDocumento() {
		return tamanoNumeroDocumento;
	}

	public void setTamanoNumeroDocumento(Integer tamanoNumeroDocumento) {
		this.tamanoNumeroDocumento = tamanoNumeroDocumento;
	}

	public Cliente getClienteEliminar() {
		return clienteEliminar;
	}

	public void setClienteEliminar(Cliente clienteEliminar) {
		this.clienteEliminar = clienteEliminar;
	}
	
}
