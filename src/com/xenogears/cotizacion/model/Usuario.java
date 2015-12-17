package com.xenogears.cotizacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer IdUsuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idVendedor")
    private Vendedor vendedor;
	
	@Column(name="correo")
    private String correo;
	
	@Column(name="clave")
    private String clave;
	
	@Column(name="flagHabilitado", insertable=false)
    private boolean flagHabilitado;
	
	@Column(name="flagEstado", insertable=false)
    private boolean flagEstado;
	
	@Column(name="flagNuevo", insertable=false)
    private boolean flagNuevo;
	
	@Transient
	private String claveSinEncriptar;

	public Integer getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		IdUsuario = idUsuario;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isFlagHabilitado() {
		return flagHabilitado;
	}

	public void setFlagHabilitado(boolean flagHabilitado) {
		this.flagHabilitado = flagHabilitado;
	}

	public boolean isFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(boolean flagEstado) {
		this.flagEstado = flagEstado;
	}

	public String getClaveSinEncriptar() {
		return claveSinEncriptar;
	}

	public void setClaveSinEncriptar(String claveSinEncriptar) {
		this.claveSinEncriptar = claveSinEncriptar;
	}

	public boolean isFlagNuevo() {
		return flagNuevo;
	}

	public void setFlagNuevo(boolean flagNuevo) {
		this.flagNuevo = flagNuevo;
	}
	
}
