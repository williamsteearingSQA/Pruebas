package com.sqa.pco.configuracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.sqa.pco.dto.Respuesta;

/**
 * Esta clase contiene el emtodo para capturar la informacion del archivo
 * .config
 * 
 * @author William Steearing Cordoba Mosquera
 * @version 1.0, 27/12/2018
 *
 */

public class Configuracion {

	public Respuesta leerArchivo(String valor) {
		Respuesta respuesta = new Respuesta();
		try {

			Properties config = new Properties();
			config.load(new FileInputStream("C:\\ConfiguracionPruebas\\ConfiguracionPortal.config"));
			String dato = config.getProperty(valor);
			respuesta.setCodigo(0);
			respuesta.setMensaje(dato);
			return respuesta;

		} catch (IOException e) {
			System.err.println(e);
			respuesta.setCodigo(1);
			respuesta.setMensaje(null);
			return respuesta;
		}

	}
}
