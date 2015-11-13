package com.xenogears.cotizacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="auto")
public class Auto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idAuto")
	private Integer idAuto;
	
	@Column(name="codigoAuto")
	private String codigoAuto;
	
	@JoinColumn(name="idTipoAuto")
	private Integer idTipoAuto;
	
	@Column(name="descripcionTipo")
	private String descripcionTipo;
	
	@JoinColumn(name="idMarca")
	private Integer idMarca;
	
	@Column(name="marca")
	private String marca;
	
	@JoinColumn(name="idModelo")
	private Integer idModelo;
	
	@Column(name="modelo")
	private String modelo;
	
	@Column(name="anio")
    private Integer anio;
	
	@Column(name="precio")
    private Double precio;
	
	@Column(name="urlFoto")
    private String urlFoto;
	
	@Column(name="flagEstado", insertable=false)
	private boolean flagEstado;

	public Integer getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(Integer idAuto) {
		this.idAuto = idAuto;
	}

	public String getCodigoAuto() {
		return codigoAuto;
	}

	public void setCodigoAuto(String codigoAuto) {
		this.codigoAuto = codigoAuto;
	}

	public Integer getIdTipoAuto() {
		return idTipoAuto;
	}

	public void setIdTipoAuto(Integer idTipoAuto) {
		this.idTipoAuto = idTipoAuto;
	}

	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public boolean isFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(boolean flagEstado) {
		this.flagEstado = flagEstado;
	}
	
}
