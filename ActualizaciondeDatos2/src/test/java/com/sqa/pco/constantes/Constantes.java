package com.sqa.pco.constantes;

import java.nio.channels.IllegalSelectorException;

/**
 * Esta clase contiene las variables que se utilizaran tanto para la captura de
 * datos desde el archivo de configuracion como de los metodos
 * 
 * @author William Steearing Cordoba Mosquera
 * @version 1.0, 27/12/2018
 */

public class Constantes {

	/**
	 * Constructor privado
	 */
	private Constantes() {
		throw new IllegalSelectorException();
	}

	public static final String WEBDRIVER = "webdriver.chrome.driver";
	public static final String RUTADRIVER = "rutaDriver";
	public static final String URLLOGINGMAIL = "urlLoginGmail";
	public static final String URLPORTAL = "https://puntos:nvD2MVBCaY0fKv1Y@pco-uat.rewardops.io/";
	public static final String EMAIL = "email";
	public static final String CLAVE = "clave";
	public static final String NOTIFICACIONPUNTOS = "Notificaciones Punt.";
	public static final String MSGCODIGOINCORRECTO = "El código ingresado es incorrecto.";
	public static final String ENLACECREACONTRASENA = "https://puntos:nvD2MVBCaY0fKv1Y@pco-uat.rewardops.io/crea-contrasena";
	public static final String ENLACEREGISTRO = "https://puntos:nvD2MVBCaY0fKv1Y@pco-uat.rewardops.io/registro";
	public static final String INFORMACIONNOVALIDA = "No hay información de contacto válida";
	public static final String MSGDATOSNOCOINCIDEN = "Uno o varios de los datos no coinciden.";
	public static final String RUTAARCHIVO = "rutaArchivo";
	public static final String LINKIDENTIDADVERIFICACION = "https://puntos:nvD2MVBCaY0fKv1Y@pco-uat.rewardops.io/identidad-verificaci%C3%B3n";
	public static final String LINKVERIFICARCONTRASENA = "https://puntos:nvD2MVBCaY0fKv1Y@pco-uat.rewardops.io/verificar-la-contrase%C3%B1a";
	public static final String LINKDOCUMENTOVERIFICACION = "https://puntos:nvD2MVBCaY0fKv1Y@pco-uat.rewardops.io/documento-verificaci%C3%B3n";
	public static final String LINKPUNTOSEXISTENTES = "https://puntos:nvD2MVBCaY0fKv1Y@pco-uat.rewardops.io/puntos-existentes";
	public static final String RUTAARCHIVOLOG = "rutaArchivoLog";
	public static final String CLIENTID = "clienteId";
	public static final String CLIENTSECRET = "clienteSecret";
	public static final String URLTOKEN = "urlToken";
	public static final String RUTACERTIFICADO = "rutaCertificado";
	public static final String CLAVECERTIFICADO = "claveCertificado";
	public static final String RUTACACERTSJAVA = "rutaCACertJava";
	public static final String CLAVECACERTSJAVA = "claveCACertJava";
	public static final String CODIGOEXITOSO = "0";
	public static final String CODIGOFALLIDO = "1";
	public static final String CLAVEINCORRECTA = "usuario o contraseña incorrectos";
	public static final String CLAVEBLOQUEADA = "Por tu seguridad, bloqueamos tu cuenta por llegar al máximo de intentos. Si no recuerdas tu clave, comunícate con nuestra línea 01 8000 180 698 o espera 24 horas para volver a intentarlo.";

}