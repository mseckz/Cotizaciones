package com.xenogears.cotizacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="sucursal")
public class Sucursal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSucursal;
	
	@JoinColumn(name="codigoDepartamento")
	private Departamento departamento;
	
	@JoinColumn(name="codigoProvincia")
	private Provincia provincia;
	
	@JoinColumn(name="codigoUbigeo")
	private Ubigeo ubigeo;
	
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
	
	public Departamento getDepartamento() {
		if(departamento == null) departamento = new Departamento();
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Provincia getProvincia() {
		if(provincia == null) provincia = new Provincia();
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Ubigeo getUbigeo() {
		if(ubigeo == null) ubigeo = new Ubigeo();
		return ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
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
