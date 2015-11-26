package com.xenogears.cotizacion.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
	
	/**
	 * Sube un archivo al servidor en una ruta indicada.
	 * @param fileName es el nombre del archivo con extension.
	 * @param in es el flujo de datos del archivo
	 * @param path es la ruta fisica del servidor.
	 * @return retorna true si se subio correctamente , caso contrario retornara false .
	 */
	public static boolean subirArchivo(String fileName, InputStream in, String path) {
		boolean resultado=false;
		OutputStream out=null;
		try {			
			out = new FileOutputStream(new File(path + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			System.out.println("Archivo Creado !");
			resultado=true;
		} catch (IOException e) {
			System.out.println(e.getMessage());			
		}
		finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.flush();
					out.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
}
