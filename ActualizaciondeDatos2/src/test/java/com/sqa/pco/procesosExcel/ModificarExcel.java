package com.sqa.pco.procesosExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sqa.pco.configuracion.Configuracion;
import com.sqa.pco.constantes.Constantes;
import com.sqa.pco.dto.Respuesta;

public class ModificarExcel {

	/**
	 * Metodo que se utiliza para pintar los campos cuando este ya se ha ejecutado.
	 * 
	 * @param rutaArchivo
	 * @param i
	 * @param resultado
	 */
	public Respuesta modificarEstado() {
		Respuesta respuesta = new Respuesta();
		Configuracion configuracion = new Configuracion();
		String rutaArchivo = configuracion.leerArchivo(Constantes.RUTAARCHIVO).getMensaje();
		File ruta = new File(rutaArchivo);
		FileInputStream archivo = null;
		try {
			archivo = new FileInputStream(ruta);
		} catch (FileNotFoundException e1) {
			System.err.println(e1.getMessage());
		}
		XSSFWorkbook libroTrabajo = null;
		try {
			libroTrabajo = new XSSFWorkbook(archivo);
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
		}
		XSSFSheet hoja;
		hoja = libroTrabajo.getSheetAt(0);
		XSSFCellStyle estilo = libroTrabajo.createCellStyle();
		try {
			int uf = hoja.getLastRowNum();
			int fila = 1;
			while (fila <= uf) {
				double datoUsado = hoja.getRow(fila).getCell(14).getNumericCellValue();
				if (datoUsado == 0) {
					hoja.getRow(fila).createCell(14);
					hoja.getRow(fila).getCell(14).setCellValue(1);
					estilo.setAlignment(HorizontalAlignment.CENTER);
					estilo.setVerticalAlignment(VerticalAlignment.CENTER);
					hoja.getRow(fila).getCell(14).setCellStyle(estilo);
					respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOEXITOSO));
					respuesta.setMensaje("Modificación exitosa");
				} else {
					fila++;
				}
			}
		} catch (Exception e) {
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
			respuesta.setMensaje("Fallo la modificación");
			System.out.println("Se produce el error: " + e.getMessage());
		}
		FileOutputStream archivoOut = null;
		try {
			archivoOut = new FileOutputStream(ruta);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		try {
			libroTrabajo.write(archivoOut);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		try {
			archivoOut.flush();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		try {
			archivoOut.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return respuesta;
	}

}
