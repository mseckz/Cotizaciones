package com.xenogears.cotizacion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Auditoria")
public class Auditoria {
	
	@Id
	@GeneratedValue
	@Column(name="idAuditoria")
	private Integer idAuditoria;
	
	@Column(name="idUsuario")
	private Integer idUsuario;
	
	@Column(name="idRegistroTabla")
	private Integer idRegistroTabla;
	
	@Column(name="nombreTabla")
	private String nombreTabla;
	
	@Column(name="fechaRegistro", insertable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	
	@Column(name="operacion")
	private String operacion;

	public Integer getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(Integer idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdRegistroTabla() {
		return idRegistroTabla;
	}

	public void setIdRegistroTabla(Integer idRegistroTabla) {
		this.idRegistroTabla = idRegistroTabla;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
}
