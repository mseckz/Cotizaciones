package com.xenogears.cotizacion.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class Util {

	/**
	 * Sube un archivo al servidor en una ruta indicada.
	 * 
	 * @param fileName
	 *            es el nombre del archivo con extension.
	 * @param in
	 *            es el flujo de datos del archivo
	 * @param path
	 *            es la ruta fisica del servidor.
	 * @return retorna true si se subio correctamente , caso contrario retornara
	 *         false .
	 */
	public static boolean subirArchivo(String fileName, InputStream in,
			String path) {
		boolean resultado = false;
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(path + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			System.out.println("Archivo Creado !");
			resultado = true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
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

	/**
	 * 
	 * @return
	 */
	public static String generarRandomString() {
		return RandomStringUtils.random(8, 0, 0, true, true, null,
				new SecureRandom());
	}

	public static String encriptarPassword(String password, String salt) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			// Add password bytes to digest
			md.update(salt.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest(password.getBytes());
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static void main(String[] args) {

		System.out.println(encriptarPassword("123456", "luis@gmail.com"));
	}
}
