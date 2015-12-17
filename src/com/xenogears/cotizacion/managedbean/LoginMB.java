package com.xenogears.cotizacion.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.xenogears.cotizacion.model.Usuario;
import com.xenogears.cotizacion.model.Vendedor;
import com.xenogears.cotizacion.service.UsuarioService;
import com.xenogears.cotizacion.util.Util;

@ManagedBean
@SessionScoped
public class LoginMB {

	private String correo;
	private String password;
	private String newpassword;
	private Vendedor vendedorAuth;
	private Usuario usuario;
	
	@ManagedProperty(value="#{usuarioService}")
    private UsuarioService service;
	
	public String login(){
		String claveEncriptada = Util.encriptarPassword(password, correo); 
		Usuario user = service.getUsuarioRepository().validarLogin(correo, claveEncriptada);
		if(user == null){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Credenciales incorrectas");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		else{
			usuario = service.getUsuarioRepository().obtenerUsuario(correo);
			vendedorAuth = usuario.getVendedor();
			
			if (usuario.isFlagNuevo()==true) {
				return "/paginas/index.xhtml?faces-redirect=true";
			}else {
				 popupCambiarPassword();
				 return "null";
			}
		}
	}

	public void cambiarPassword(){
			newpassword=Util.encriptarPassword(newpassword, usuario.getCorreo());
			usuario.setClave(newpassword);
			usuario.setFlagNuevo(true);
			usuario=service.getUsuarioRepository().save(usuario);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Contraseña cambiada correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			usuario=new Usuario();
	}
	public void popupCambiarPassword(){
		RequestContext.getCurrentInstance().execute("PF('w_cambiarPasswordDialog').show();");
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String redireccionarDashboard(){
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

	public Vendedor getVendedorAuth() {
		return vendedorAuth;
	}

	public void setVendedorAuth(Vendedor vendedorAuth) {
		this.vendedorAuth = vendedorAuth;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
}
