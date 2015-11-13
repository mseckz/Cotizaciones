package com.xenogears.cotizacion.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.Sucursal;
import com.xenogears.cotizacion.model.Usuario;
import com.xenogears.cotizacion.model.Vendedor;
import com.xenogears.cotizacion.service.SucursalService;
import com.xenogears.cotizacion.service.UsuarioService;
import com.xenogears.cotizacion.service.VendedorService;

@ManagedBean
@SessionScoped
public class VendedorManagedBean {
	
	private Vendedor vendedor;
	private Usuario usuario;
	private List<Sucursal> sucursales;
	private List<Vendedor> vendedores;

	@ManagedProperty(value="#{vendedorService}")
	VendedorService servicio;
	
	@ManagedProperty(value="#{sucursalService}")
	SucursalService sucursalService;
	
	@ManagedProperty(value="#{usuarioService}")
	UsuarioService usuarioService;
	
	@PostConstruct
	public void init(){
		vendedor = new Vendedor();
		usuario = new Usuario();
		vendedores = new ArrayList<Vendedor>();
	}
	
	public String indexVendedor(){
		return "/paginas/mantenimiento/vendedor/vendedorIndex.xhtml?faces-redirect=true";
	}
	
	public String grabar(){
		Vendedor vend = servicio.getVendedorRepository().save(vendedor);
		usuario.setCorreo(vend.getCorreo());
		usuario.setVendedor(vend);
		usuarioService.getUsuarioRepository().save(usuario);
		usuario = new Usuario();
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
		Usuario usuario = usuarioService.getUsuarioRepository().obtenerUsuario(vend.getCorreo());
		usuario.setFlagEstado(false);
		usuarioService.getUsuarioRepository().save(usuario);
		vendedores = Lists.newArrayList(servicio.getVendedorRepository().obtenerPorEstado(true));
		return null;
	}
	
	public String deshabilitar(Integer vendedor){
		return null;
	}
	
	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public List<Sucursal> getSucursales() {
		sucursales = sucursalService.listarSucursales();
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
}
