package com.xenogears.cotizacion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="vendedor")
public class Vendedor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idVendedor")
	private Integer idVendedor;
	
	@OneToOne
	@JoinColumn(name="idSupervisor")
	private Vendedor Supervisor;
	
	@Column(name="codigoVendedor")
    private String codigoVendedor;
	
	@Column(name="docIdentidad")
	private String docIdentidad;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellidoPaterno")
	private String apellidoPaterno;

	@Column(name="apellidoMaterno")
	private String apellidoMaterno;
	
	@Column(name="telefono")
    private String telefono;
	
	@Column(name="correo")
	private String correo;
	
	@JoinColumn(name="idSucursal")
    private Integer idSucursal;
	
	@Column(name="nombreSucursal")
	private String nombreSucursal;
	
	@Column(name="fechaNacimiento")
	@Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
	
	@Column(name="fechaRegistro", insertable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	
	@Column(name="flagHabilitado", insertable=false)
	private boolean flagHabilitado;
	
	@Column(name="flagEstado", insertable=false)
	private boolean flagEstado;

	public Integer getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Vendedor getSupervisor() {
		return Supervisor;
	}

	public void setSupervisor(Vendedor supervisor) {
		Supervisor = supervisor;
	}

	public String getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(String codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public String getDocIdentidad() {
		return docIdentidad;
	}

	public void setDocIdentidad(String docIdentidad) {
		this.docIdentidad = docIdentidad;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(boolean flagEstado) {
		this.flagEstado = flagEstado;
	}

}
