package com.xenogears.cotizacion.repository;

import com.xenogears.cotizacion.model.Usuario;

public interface UsuarioRepositoryCustom {
	
	Usuario validarLogin(String correo, String password);
	
	Usuario obtenerUsuario(String correo);
	
	Usuario obtenerUsuarioxIdVend(Integer idVendedor);
}
