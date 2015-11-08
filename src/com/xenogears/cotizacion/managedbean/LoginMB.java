package com.xenogears.cotizacion.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.xenogears.cotizacion.model.Usuario;
import com.xenogears.cotizacion.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginMB {
	
	@ManagedProperty(value="#{usuarioService}")
    private UsuarioService service;

	
	String correo;
	String password;
	
	public String login(){
		Usuario user = service.getUsuarioRepository().validarLogin(correo, password);
		if(user == null) System.out.println("error");
		else System.out.println(user.getCorreo() + " - " + user.getClave());
		return "/paginas/index.xhtml?faces-redirect=true";
	}
	
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}
	
}
