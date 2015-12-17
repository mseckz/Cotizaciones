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
	
	@PostConstruct
	public void init(){
		this.vendedorSeleccionado = loginManagedBean.getVendedorAuth();
		cotizacion.setImporte(0.0);
	}
	
	public String registrarCotizacion() throws IOException, DocumentException{
	
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
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Cotizacion realizada correctamente");
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
		DetalleCotizacion det = new DetalleCotizacion();
		det.setAuto(autoSeleccionado);
		det.setCotizacion(cotizacion);
		det.setCantidad(this.cantidad);
		det.setPrecio(autoSeleccionado.getPrecio());
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
	
	public void limpiarForm(){
		cotizacion = new Cotizacion();
		autoSeleccionado = new Auto();
		clienteSeleccionado = new Cliente();
		listaDetalle = new ArrayList<DetalleCotizacion>();
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
	
}
