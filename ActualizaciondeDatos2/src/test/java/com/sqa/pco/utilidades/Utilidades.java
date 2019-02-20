package com.sqa.pco.utilidades;

import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sqa.pco.constantes.Constantes;
import com.sqa.pco.dto.Respuesta;
import com.sqa.pco.pom.Pom;
import com.sqa.pco.procesosExcel.LeerExcel;

public class Utilidades {

	/**
	 * Metodo para seleccionar una opción en una lista.
	 * 
	 * @param driver
	 * @param id
	 * @param valor
	 */
	public void select(WebDriver driver, String id, String valor) {
		Select scl = new Select(driver.findElement(By.id(id)));
		scl.selectByVisibleText(valor);
	}

	public Respuesta loginPortal(WebDriver driver) {
		Utilidades utilidades = new Utilidades();
		LeerExcel leerDataDriven = new LeerExcel();
		Respuesta respuesta = new Respuesta();
		try {
			Thread.sleep(3000);
			Pom.btnRegistrate(driver).click();
			TreeMap<String, String> datos = leerDataDriven.leerDataDriven();
			utilidades.select(driver, "document-type-select", datos.get("tipoDocumento"));
			Pom.txtDocumento(driver).click();
			Pom.txtDocumento(driver).clear();
			Pom.txtDocumento(driver).sendKeys(datos.get("documento"));
			Pom.btnLoginButton(driver).submit();
			WebDriverWait driverWait = new WebDriverWait(driver, 10);
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-password")));
			Pom.txtClaveLogin(driver).sendKeys(datos.get("clave"));
			Pom.btnContinuar(driver).submit();
			Thread.sleep(2000);
			try {
				String texto = Pom.lblDatosIncorrectos(driver).getAttribute("innerText");
				System.out.println(texto);
				if ((texto.equalsIgnoreCase(Constantes.CLAVEBLOQUEADA))
						|| (texto.equalsIgnoreCase(Constantes.CLAVEINCORRECTA))) {
					respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
					respuesta.setMensaje("Logueo Fallido");
				}
			} catch (Exception e) {
				respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOEXITOSO));
				respuesta.setMensaje("Logueo exitoso");
			}
		} catch (Exception e) {
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
			respuesta.setMensaje("Logueo Fallido");

		}
		return respuesta;
	}

	/**
	 * Método de tipo respuesta que realiza la actualización de los datos en el
	 * portal
	 * 
	 * @param driver
	 * @param datos
	 * @param nombreArchivo
	 * @return respuesta Nota: Este metodo devuelve un codigo y un mensaje. ver
	 *         clase Respuesta
	 */
	public Respuesta actualizacion(WebDriver driver, TreeMap<String, String> datos) {
		Respuesta respuesta = new Respuesta();
		try {
			Pom.btnUsuarioNavegacion(driver).click();
			Pom.lnkMiCuenta(driver).click();
			WebDriverWait driverWait = new WebDriverWait(driver, 10);
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("landlinePhone")));
			Pom.txtTelefono(driver).clear();
			Pom.txtTelefono(driver).sendKeys(datos.get("telefono"));
			Pom.txtDireccion(driver).clear();
			Pom.txtDireccion(driver).sendKeys(datos.get("direccion"));
			select(driver, "department", datos.get("departamento"));
			select(driver, "cityCode", datos.get("ciudad"));
			Pom.txtFechaNacimientoRegistro(driver).clear();
			Pom.txtFechaNacimientoRegistro(driver).sendKeys(datos.get("fechaNacimiento"));
			select(driver, "gender", datos.get("genero"));
			select(driver, "civilStatus", datos.get("estadoCivil"));
			select(driver, "occupation", datos.get("ocupacion"));
			Pom.btnBotonGuardar(driver).submit();
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOEXITOSO));
			respuesta.setMensaje("Actualización Exitosa");
			/*
			 * try { if
			 * (Pom.btnBotonGuardar(driver).getAttribute("disabled").equals("true")) {
			 * respuesta.setCodigo(1); respuesta.setMensaje("Campos incompletos"); return
			 * respuesta; } else { respuesta.setCodigo(0); respuesta.
			 * setMensaje("Proceso de actualización en el portal fue satisfactorio"); return
			 * respuesta; } } catch (Exception e) { Pom.btnBotonGuardar(driver).click();
			 * respuesta.setCodigo(0); respuesta.
			 * setMensaje("Proceso de actualización en el portal fue satisfactorio"); return
			 * respuesta; }
			 */
		} catch (Exception e) {
			System.out.println("Uno de los campos no existe");
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
			respuesta.setMensaje("Uno de los campos no existe");
		}
		return respuesta;
	}

	public Respuesta logout(WebDriver driver) throws InterruptedException {
		Respuesta respuesta = new Respuesta();
		try {
			Pom.btnDeslogueo(driver).click();
			Thread.sleep(1000);
			Pom.liCerrarSesion(driver).click();
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOEXITOSO));
			respuesta.setMensaje("Logout exitoso");
		} catch (Exception e) {
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
			respuesta.setMensaje("Logout fallido");
		}
		return respuesta;

	}

	public Respuesta cerrarChome(WebDriver driver) throws InterruptedException {
		Respuesta respuesta = new Respuesta();
		try {
			driver.close();
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOEXITOSO));
			respuesta.setMensaje("Cierre exitoso");
		} catch (Exception e) {
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
			respuesta.setMensaje("Cierre fallido");
		}
		return respuesta;
	}

}
