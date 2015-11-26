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

@Entity
@Table(name="configVariable")
public class ConfigVariable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idConfigVariable")
	private Integer idConfigVariable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPadre")
    private ConfigVariable padre;
	
	@Column(name="codigo")
    private String codigo;
	
	@Column(name="descripcion")
    private String descripcion;
	
	@Column(name="flagEstado", insertable=false)
    private boolean flagEstado;

	public Integer getIdConfigVariable() {
		return idConfigVariable;
	}

	public void setIdConfigVariable(Integer idConfigVariable) {
		this.idConfigVariable = idConfigVariable;
	}

	public ConfigVariable getPadre() {
		if(padre == null) padre = new ConfigVariable();
		return padre;
	}

	public void setPadre(ConfigVariable padre) {
		this.padre = padre;
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
