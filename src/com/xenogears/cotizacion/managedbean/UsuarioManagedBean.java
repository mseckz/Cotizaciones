package com.xenogears.cotizacion.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.xenogears.cotizacion.model.Usuario;
import com.xenogears.cotizacion.service.UsuarioService;

@ManagedBean
@SessionScoped
public class UsuarioManagedBean {
	
	private Usuario usuario;
	
	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService servicio;
	
	public String registrarUsuario(){
		servicio.getUsuarioRepository().save(usuario);
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getServicio() {
		return servicio;
	}

	public void setServicio(UsuarioService servicio) {
		this.servicio = servicio;
	}
	
}
