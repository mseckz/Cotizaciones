package com.xenogears.cotizacion.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@JoinColumn(name="idTipoMoneda")
	private Integer idTipoMoneda;
	
	@Column(name="descripTipoMoneda")
	private String descripTipoMoneda;
	
	@Column(name="precio")
	private Double precio;
	
	@Column(name="importe")
	private Double importe;
	
	@Column(name="flagAprobado", insertable=false)
	private boolean flagAprobado;
	
	@Column(name="flagEstado", insertable=false)
	private boolean flagEstado;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cotizacion",cascade= {CascadeType.PERSIST})
	private List<DetalleCotizacion> detalle;

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

	public List<DetalleCotizacion> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleCotizacion> detalle) {
		this.detalle = detalle;
	}
	
}
