package com.xenogears.cotizacion.repository;

public interface VendedorRepositoryCustom {
	
	String obtenerCodigo();
	
	boolean correValido(String correo);
}
