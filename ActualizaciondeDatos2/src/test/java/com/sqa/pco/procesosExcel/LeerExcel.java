package com.sqa.pco.procesosExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sqa.pco.configuracion.Configuracion;
import com.sqa.pco.constantes.Constantes;

/**
 * Esta clase contiene los procesos que se realizan con el datadriven en formato
 * excel.xlsx(Apache POI)
 * 
 * @author William Steearing Cordoba Mosquera
 * @version 1.0, 27/12/2018
 */

public class LeerExcel {

	/**
	 * Metodo utilizado para la lectura y captura del datadriven
	 * 
	 * @param driver
	 * @param nombreArchivo
	 * @throws IOException
	 */
	public TreeMap<String, String> leerDataDriven() throws IOException {
		Configuracion configuracion = new Configuracion();
		try {
			String rutaArchivo = configuracion.leerArchivo(Constantes.RUTAARCHIVO).getMensaje();
			File ruta = new File(rutaArchivo);
			FileInputStream archivo = new FileInputStream(ruta);
			@SuppressWarnings("resource")
			XSSFWorkbook libroTrabajo = new XSSFWorkbook(archivo);
			XSSFSheet hoja = libroTrabajo.getSheetAt(0);
			int uf = hoja.getLastRowNum();
			int fila = 1;
			while (fila <= uf) {
				double datoUsado = hoja.getRow(fila).getCell(11).getNumericCellValue();
				if (datoUsado == 0) {
					String documento = hoja.getRow(fila).getCell(1).getStringCellValue();
					if (documento != null) {
						TreeMap<String, String> datos = new TreeMap<String, String>();
						datos.put("tipoDocumento", hoja.getRow(fila).getCell(0).getStringCellValue());
						datos.put("documento", hoja.getRow(fila).getCell(1).getStringCellValue());
						datos.put("clave", hoja.getRow(fila).getCell(2).getStringCellValue());
						datos.put("fechaNacimiento", hoja.getRow(fila).getCell(3).getStringCellValue());
						datos.put("genero", hoja.getRow(fila).getCell(4).getStringCellValue());
						datos.put("telefono", hoja.getRow(fila).getCell(5).getStringCellValue());
						datos.put("direccion", hoja.getRow(fila).getCell(6).getStringCellValue());
						datos.put("ocupacion", hoja.getRow(fila).getCell(7).getStringCellValue());
						datos.put("estadoCivil", hoja.getRow(fila).getCell(8).getStringCellValue());
						datos.put("departamento", hoja.getRow(fila).getCell(9).getStringCellValue());
						datos.put("ciudad", hoja.getRow(fila).getCell(10).getStringCellValue());
						return datos;
					} else {
						fila = uf + 1;
					}
				} else {
					fila++;
				}
			}
		} catch (Exception e) {
			System.out.println("El proceso termino con errores: " + e.getMessage());
			return null;
		}
		return null;
	}
}
