package com.xenogears.cotizacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="configVariable")
public class ConfigVariable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idConfigVariable")
	private Integer idConfigVariable;
	
	@Column(name="idPadre")
    private Integer idPadre;
	
	@Column(name="codigo")
    private String codigo;
	
	@Column(name="descripcion")
    private String descripcion;
	
	@Column(name="flagEstado")
    private boolean flagEstado;

	public Integer getIdConfigVariable() {
		return idConfigVariable;
	}

	public void setIdConfigVariable(Integer idConfigVariable) {
		this.idConfigVariable = idConfigVariable;
	}

	public Integer getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(boolean flagEstado) {
		this.flagEstado = flagEstado;
	}
	
}
