package com.xenogears.cotizacion.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.xenogears.cotizacion.model.Usuario;
import com.xenogears.cotizacion.model.Vendedor;
import com.xenogears.cotizacion.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginMB {

	private String correo;
	private String password;
	private Vendedor vendedorAuth;
	
	@ManagedProperty(value="#{usuarioService}")
    private UsuarioService service;
	
	
	public String login(){
		Usuario user = service.getUsuarioRepository().validarLogin(correo, password);
		if(user == null){
			System.out.println("asd");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Credenciales incorrectas");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		else{
			Usuario usuario = service.getUsuarioRepository().obtenerUsuario(correo);
			vendedorAuth = usuario.getVendedor();
			return "/paginas/index.xhtml?faces-redirect=true";
		}
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
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

	public Vendedor getVendedorAuth() {
		return vendedorAuth;
	}

	public void setVendedorAuth(Vendedor vendedorAuth) {
		this.vendedorAuth = vendedorAuth;
	}
	
}
