package com.xenogears.cotizacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cotizacion")
public class Cotizacion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCotizacion")
	private Integer idCotizacion;
	
	@Column(name="codigoCotizacion")
	private String codigoCotizacion;
	
	@ManyToOne
	@JoinColumn(name="idVendedor")
    private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
    private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idAuto")
    private Auto auto;
	
	@JoinColumn(name="idTipoMoneda")
	private Integer idTipoMoneda;
	
	@Column(name="descripTipoMoneda")
	private String descripTipoMoneda;
	
	@Column(name="cantidad")
    private Integer cantidad;
	
	@Column(name="precio")
	private Double precio;
	
	@Column(name="importe")
	private Double importe;
	
	@Column(name="flagAprobado", insertable=false)
	private boolean flagAprobado;
	
	@Column(name="flagEstado", insertable=false)
	private boolean flagEstado;

	public Integer getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Integer idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public String getCodigoCotizacion() {
		return codigoCotizacion;
	}

	public void setCodigoCotizacion(String codigoCotizacion) {
		this.codigoCotizacion = codigoCotizacion;
	}

	public Vendedor getVendedor() {
		if(vendedor == null) vendedor = new Vendedor();
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		if(cliente == null) cliente = new Cliente();
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Auto getAuto() {
		if(auto == null) auto = new Auto();
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public Integer getIdTipoMoneda() {
		return idTipoMoneda;
	}

	public void setIdTipoMoneda(Integer idTipoMoneda) {
		this.idTipoMoneda = idTipoMoneda;
	}

	public String getDescripTipoMoneda() {
		return descripTipoMoneda;
	}

	public void setDescripTipoMoneda(String descripTipoMoneda) {
		this.descripTipoMoneda = descripTipoMoneda;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public boolean isFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(boolean flagEstado) {
		this.flagEstado = flagEstado;
	}

	public boolean isFlagAprobado() {
		return flagAprobado;
	}

	public void setFlagAprobado(boolean flagAprobado) {
		this.flagAprobado = flagAprobado;
	}
	
}
