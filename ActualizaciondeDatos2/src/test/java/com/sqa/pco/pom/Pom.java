package com.sqa.pco.pom;

import java.nio.channels.IllegalSelectorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Esta clase contiene todos los objetos con los que interactúa la pagina
 * POM(Page Objects Model)
 * 
 * @author William Steearing Cordoba Mosquera
 * @version 1.0, 28/08/2018
 *
 */
public class Pom {

	/**
	 * Constructor privado
	 */

	private Pom() {
		throw new IllegalSelectorException();
	}

	private static WebElement elemento;

	public static WebElement btnRegistrate(WebDriver driver) {
		elemento = driver.findElement(By.id("open-modal-button"));
		return elemento;
	}

	public static WebElement sltTipoDocumento(WebDriver driver) {
		elemento = driver.findElement(By.id("document-type-select"));
		return elemento;
	}

	public static WebElement txtDocumento(WebDriver driver) {
		elemento = driver.findElement(By.id("document-number-input"));
		return elemento;
	}

	public static WebElement btnLoginButton(WebDriver driver) {
		elemento = driver.findElement(By.id("login-button"));
		return elemento;
	}

	public static WebElement btnComenzar(WebDriver driver) {
		elemento = driver.findElement(By.xpath("//*[@id=\"term-and-conditions\"]/div[6]/button"));
		return elemento;
	}

	public static WebElement txtCorreo(WebDriver driver) {
		elemento = driver.findElement(By.id("email"));
		return elemento;
	}

	public static WebElement txtCelular(WebDriver driver) {
		elemento = driver.findElement(By.id("cellPhone"));
		return elemento;
	}

	public static WebElement txtTelefono(WebDriver driver) {
		elemento = driver.findElement(By.id("landlinePhone"));
		return elemento;
	}

	public static WebElement txtDireccion(WebDriver driver) {
		elemento = driver.findElement(By.id("address"));
		return elemento;
	}

	public static WebElement txtFechaNacimientoRegistro(WebDriver driver) {
		elemento = driver.findElement(By.id("birthDate"));
		return elemento;
	}

	public static WebElement txtClaveLogin(WebDriver driver) {
		elemento = driver.findElement(By.id("login-password"));
		return elemento;
	}

	public static WebElement btnCerrarModal(WebDriver driver) {
		elemento = driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div/button/img"));
		return elemento;
	}

	public static WebElement btnCerrarSesion(WebDriver driver) {
		elemento = driver
				.findElement(By.xpath("/html/body/app-root/div/main-nav/nav/div[2]/profile/div/div/ul/li[3]/a"));
		return elemento;
	}

	public static WebElement btnCerrarUsuarioInvalido(WebDriver driver) {
		elemento = driver.findElement(By.xpath("/html/body/app-root/invalid-user-modal/div/div/div/div/div[2]/button"));
		return elemento;
	}

	public static WebElement lnkMiCuenta(WebDriver driver) {
		elemento = driver.findElement(By.xpath("//*[@id=\"main-nav-profile-link\"]/span"));
		return elemento;
	}

	public static WebElement btnBotonGuardar(WebDriver driver) {
		elemento = driver.findElement(By.id("saveUserButton"));
		return elemento;
	}

	public static WebElement btnContinuar(WebDriver driver) {
		elemento = driver.findElement(By.id("continue-button"));
		return elemento;
	}

	public static WebElement btnUsuarioNavegacion(WebDriver driver) {
		elemento = driver.findElement(By.id("user-navigation-section"));
		return elemento;
	}

	public static WebElement lblLoginFallidoClave(WebDriver driver) {
		elemento = driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div/div/p[1]"));
		return elemento;
	}

	public static WebElement lblLoginFallidoBloqueado(WebDriver driver) {
		elemento = driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div/div/p"));
		return elemento;
	}

	public static WebElement btnDeslogueo(WebDriver driver) {
		elemento = driver.findElement(By.id("user-navigation-section"));
		return elemento;
	}

	public static WebElement liCerrarSesion(WebDriver driver) {
		elemento = driver
				.findElement(By.xpath("/html/body/app-root/div[1]/main-nav/nav/div[2]/profile/div/div/ul/li[3]"));
		return elemento;
	}

	public static WebElement lblDatosIncorrectos(WebDriver driver) {
		elemento = driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div/div/p[1]"));
		return elemento;
	}

}
