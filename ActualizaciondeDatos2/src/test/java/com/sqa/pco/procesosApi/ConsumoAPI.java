package com.sqa.pco.procesosApi;

import java.util.TreeMap;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.sqa.pco.configuracion.Configuracion;
import com.sqa.pco.constantes.Constantes;
import com.sqa.pco.dto.Respuesta;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConsumoAPI {

	/**
	 * Método tipo respuesta que realiza la ejecución de la API y devuelve.
	 *
	 * @param datos the datos
	 * @return respuesta
	 * @throws OAuthSystemException  the o auth system exception
	 * @throws OAuthProblemException the o auth problem exception
	 */
	public Respuesta getAccountBalance(TreeMap<String, String> datos)
			throws OAuthSystemException, OAuthProblemException {
		Configuracion configuracion = new Configuracion();
		Respuesta respuesta = new Respuesta();
		String base = "https://apinp.puntoscolombia.com:8443/puntos/v1";
		String nombreAPI = "/accounts/" + datos.get("documento") + "/balance?documentType=2";
		Autenticacion token = new Autenticacion();
		String codigoToken = token.generarToken().getMensaje();
		try {
			/**
			 * Configuración de autenticación de la API.
			 */
			RestAssured.baseURI = base;
			RestAssured.keyStore(configuracion.leerArchivo(Constantes.RUTACERTIFICADO).getMensaje(),
					configuracion.leerArchivo(Constantes.CLAVECERTIFICADO).getMensaje());
			RestAssured.trustStore(configuracion.leerArchivo(Constantes.RUTACACERTSJAVA).getMensaje(),
					configuracion.leerArchivo(Constantes.CLAVECACERTSJAVA).getMensaje());
			RequestSpecification solicitud = RestAssured.given().auth().oauth2(codigoToken);
			Response response = solicitud.request(io.restassured.http.Method.GET, nombreAPI);
			JsonPath respuestJson = response.jsonPath();
			// System.out.println(response.getBody().asString());
			try {
				/**
				 * Captura y procesamiento de respuesta.
				 */
				Object documentNo = respuestJson.get("documentNo");
				Object addressDetails = respuestJson.get("contactInfo.addressDetails");
				Object phoneNo = respuestJson.get("contactInfo.phoneNo");
				Object resultCode = respuestJson.get("resultCode");
				Object resultMsg = respuestJson.get("resultMsg");
				if (documentNo != null) {

					if (!documentNo.toString().equals(datos.get("documento"))) {
						respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
						respuesta.setMensaje("Número de documento diferente");
						return respuesta;
					}

					if (!addressDetails.toString().equalsIgnoreCase(datos.get("direccion"))) {
						respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
						respuesta.setMensaje("Campo dirección diferente");
						return respuesta;
					}

					String telefono = phoneNo.toString().substring(4);
					if (!datos.get("telefono").equals(telefono)) {
						respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
						respuesta.setMensaje("Número telefono diferente");
						return respuesta;
					}

					respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOEXITOSO));
					respuesta.setMensaje("Los datos se actualizaron satisfactoriamente.");

				} else {
					System.out.println(resultCode);
					System.out.println(resultMsg);
					respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
					respuesta.setMensaje("La consulta arrojo el error: " + resultMsg);

				}
			} catch (Exception e) {
				respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
				respuesta.setMensaje("No se pudieron extraer los elementos del Json");

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException caught" + e.getMessage());
			respuesta.setCodigo(Integer.parseInt(Constantes.CODIGOFALLIDO));
			respuesta.setMensaje("ArrayIndexOutOfBoundsException caught" + e.getMessage());

		}
		return respuesta;
	}
}
