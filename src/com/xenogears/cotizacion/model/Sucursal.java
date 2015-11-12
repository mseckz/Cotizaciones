package com.xenogears.cotizacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sucursal")
public class Sucursal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSucursal;
	
	@Column(name="codigoUbigeo")
	private String codigoUbigeo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="flagEstado", insertable=false)
	private boolean flagEstado;

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getCodigoUbigeo() {
		return codigoUbigeo;
	}

	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(boolean flagEstado) {
		this.flagEstado = flagEstado;
	}
	
}
