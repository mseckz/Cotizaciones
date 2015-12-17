package com.xenogears.cotizacion.managedbean;

import java.io.IOException;
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
import com.lowagie.text.DocumentException;
import com.xenogears.cotizacion.model.Auto;
import com.xenogears.cotizacion.model.Cliente;
import com.xenogears.cotizacion.model.ConfigVariable;
import com.xenogears.cotizacion.model.Cotizacion;
import com.xenogears.cotizacion.model.DetalleCotizacion;
import com.xenogears.cotizacion.model.Vendedor;
import com.xenogears.cotizacion.service.AutoService;
import com.xenogears.cotizacion.service.ClienteService;
import com.xenogears.cotizacion.service.ConfigVariableService;
import com.xenogears.cotizacion.service.CotizacionService;
import com.xenogears.cotizacion.service.VendedorService;
import com.xenogears.cotizacion.util.Email;

@ManagedBean
@SessionScoped
public class AltaCotizacionManagedBean {
	Email emailsend=new Email();
	
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
	
	@ManagedProperty(value="#{loginMB}")
	private LoginMB loginManagedBean;
	
	private Integer cantidad;
	private Cotizacion cotizacion = new Cotizacion();
	private List<Auto> autos = new ArrayList<Auto>();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Vendedor> vendedores = new ArrayList<Vendedor>();
	private List<ConfigVariable> tipoMonedas = new ArrayList<ConfigVariable>();
	private Auto autoSeleccionado = new Auto();
	private Vendedor vendedorSeleccionado = new Vendedor();
	private Cliente clienteSeleccionado = new Cliente();
	private List<DetalleCotizacion> listaDetalle = new ArrayList<DetalleCotizacion>();
	private DetalleCotizacion detalleSeleccionado;
	private List<Cliente> clientesFiltrados;
	private List<Auto> autosFiltrados;
	private Cliente nuevoCliente;
	
	private Integer tamanoNumeroDocumento;
	
	@PostConstruct
	public void init(){
		this.vendedorSeleccionado = loginManagedBean.getVendedorAuth();
		cotizacion.setImporte(0.0);
	}
	
	public void seleccionarCliente(Cliente cliente){
		clienteSeleccionado=cliente;
		System.out.println(cliente);
	}
	
	public String registrarCotizacion() throws IOException, DocumentException{
		
		if(clienteSeleccionado.getIdCliente() == null){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar Cliente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
		if(listaDetalle.isEmpty()){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe ingresar autos(s)");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	
		String codigo = cotizacionService.getCotizacionRepository().obtenerCodigo();
		String numCodigo = codigo.substring(3);
		Integer codInt = Integer.parseInt(numCodigo) + 1;
		codigo = "COT" + String.format("%03d", codInt);
		
		ConfigVariable tipoMoneda = cofigVarService.getConfigVarRepository().findOne(cotizacion.getIdTipoMoneda());
		cotizacion.setDescripTipoMoneda(tipoMoneda.getDescripcion());
		cotizacion.setCodigoCotizacion(codigo);
		cotizacion.setCliente(clienteSeleccionado);
		cotizacion.setVendedor(vendedorSeleccionado);
		cotizacion.setDetalle(listaDetalle);
		
		//Envio de Correo
		cotizacionService.getCotizacionRepository().save(cotizacion);
		emailsend.sentEmailParams(cotizacion);
		
		this.limpiarForm();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cotizacion realizada correctamente");
		FacesContext.getCurrentInstance().addMessage(null, message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		return "/paginas/cotizacion/indexCotizacion.xhtml?faces-redirect=true";
	}
	
	public void popupBuscarAuto(){
		RequestContext.getCurrentInstance().execute("PF('w_buscarAutoDialog').show();");
	}
	
	public void popupBuscarCliente(){
		RequestContext.getCurrentInstance().execute("PF('w_buscarClienteDialog').show();");
	}
	
	
	public void agregarDetalle(){
		if(cantidad == null || cantidad == 0){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ingrese cantidad");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		
		DetalleCotizacion det = new DetalleCotizacion();
		det.setAuto(autoSeleccionado);
		det.setCotizacion(cotizacion);
		det.setCantidad(this.cantidad);
		det.setPrecio(autoSeleccionado.getPrecio());
		System.out.println(cantidad + " " + autoSeleccionado.getPrecio());
		det.setSubtotal(this.cantidad * autoSeleccionado.getPrecio());
		listaDetalle.add(det);
		autoSeleccionado = new Auto();
		this.cantidad = null;
		cotizacion.setImporte(cotizacion.getImporte() + det.getSubtotal());
	}
	
	public void quitarItemDetalle(){
		for(DetalleCotizacion det : listaDetalle){
			if(det.equals(detalleSeleccionado)){
				listaDetalle.remove(det);
				break;
			}
		}
	}
	
	public void popupNuevoCliente(){
		RequestContext.getCurrentInstance().execute("PF('w_nuevoClienteDialog').show();");
	}
	
	public void popupTipoCambio(){
		if(cotizacion.getIdTipoMoneda() == 8){
			RequestContext.getCurrentInstance().execute("PF('w_tipoCambioDialog').show();");
		}
	}
	
	public void limpiarForm(){
		cotizacion = new Cotizacion();
		autoSeleccionado = new Auto();
		clienteSeleccionado = new Cliente();
		listaDetalle = new ArrayList<DetalleCotizacion>();
	}
	
	//CLIENTE
	public void crearCliente(){
		String ultimoCodigo = clienteService.getClienteRepository().obtenerCodigo();
		Integer numeroCodigo = Integer.parseInt(ultimoCodigo.substring(3));
		String nuevoCodigo = "CL" + String.format("%04d", (numeroCodigo+1));
		nuevoCliente.setCodigoCliente(nuevoCodigo);
		
		//Obtener tipos
		ConfigVariable var = cofigVarService.getConfigVarRepository().findOne(nuevoCliente.getIdTipoDocumento());
		nuevoCliente.setDescripTipoDoc(var.getDescripcion());
		ConfigVariable tipoCli = cofigVarService.getConfigVarRepository().findOne(nuevoCliente.getIdTipoCliente());
		nuevoCliente.setDescripTipoCliente(tipoCli.getDescripcion());
		clienteService.getClienteRepository().save(nuevoCliente);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Cliente "+ nuevoCliente.getCodigoCliente() +" registrado correctamente");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		nuevoCliente = new Cliente();
		RequestContext.getCurrentInstance().execute("PF('w_nuevoClienteDialog').hide();");
	}
	
	public void onTipoDocChange(){
		Integer idTipoDoc = nuevoCliente.getIdTipoDocumento();
		switch(idTipoDoc){
		case 12:
			tamanoNumeroDocumento = 8;break;
		case 13:
			tamanoNumeroDocumento = 11;break;
		}
		System.out.println(tamanoNumeroDocumento);
	}
	
	public void limpiarFormCliente(){
		nuevoCliente = new Cliente();
	}
	
	//GETTERS SETTERS
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
		tipoMonedas = cofigVarService.getConfigVarRepository().obtenerPorid(7);
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

	public LoginMB getLoginManagedBean() {
		return loginManagedBean;
	}

	public void setLoginManagedBean(LoginMB loginManagedBean) {
		this.loginManagedBean = loginManagedBean;
	}

	public List<DetalleCotizacion> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<DetalleCotizacion> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public DetalleCotizacion getDetalleSeleccionado() {
		return detalleSeleccionado;
	}

	public void setDetalleSeleccionado(DetalleCotizacion detalleSeleccionado) {
		this.detalleSeleccionado = detalleSeleccionado;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public List<Auto> getAutosFiltrados() {
		return autosFiltrados;
	}

	public void setAutosFiltrados(List<Auto> autosFiltrados) {
		this.autosFiltrados = autosFiltrados;
	}

	public Cliente getNuevoCliente() {
		if(nuevoCliente == null) {
			nuevoCliente = new Cliente();
		}
		return nuevoCliente;
	}

	public void setNuevoCliente(Cliente nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
	}

	public Integer getTamanoNumeroDocumento() {
		return tamanoNumeroDocumento;
	}

	public void setTamanoNumeroDocumento(Integer tamanoNumeroDocumento) {
		this.tamanoNumeroDocumento = tamanoNumeroDocumento;
	}
	
}
