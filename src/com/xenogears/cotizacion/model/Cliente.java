package com.xenogears.cotizacion.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Entity;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCliente")
	private Integer idCliente;
	
	@Column(name="codigoCliente")
	private String codigoCliente;
	
	@Column(name="razonSocial")
    private String razonSocia;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="email")
	private String email;
	
	@Column(name="idTipoCliente")
	private Integer idTipoCliente;
	
	@Column(name="descripTipoCliente")
	private String descripTipoCliente;
	
	@Column(name="idTipoDocumento")
    private Integer idTipoDocumento;
	
	@Column(name="descripTipoDoc")
	private String descripTipoDoc;
	
	@Column(name="numeroDocumento")
	private String numeroDocumento;

	@Column(name="flagEstado", insertable=false)
	private boolean flagEstado;
	
	@Transient
	private String nombreCompleto;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getRazonSocia() {
		return razonSocia;
	}

	public void setRazonSocia(String razonSocia) {
		this.razonSocia = razonSocia;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getDescripTipoCliente() {
		return descripTipoCliente;
	}

	public void setDescripTipoCliente(String descripTipoCliente) {
		this.descripTipoCliente = descripTipoCliente;
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getDescripTipoDoc() {
		return descripTipoDoc;
	}

	public void setDescripTipoDoc(String descripTipoDoc) {
		this.descripTipoDoc = descripTipoDoc;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public boolean isFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(boolean flagEstado) {
		this.flagEstado = flagEstado;
	}

	public String getNombreCompleto() {
		if(this.nombres != null || this.apellidos != null){
			nombreCompleto = this.nombres + " " + this.apellidos;
		}
		return nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
