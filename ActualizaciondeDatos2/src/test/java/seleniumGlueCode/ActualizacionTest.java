package seleniumGlueCode;

import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sqa.pco.configuracion.Configuracion;
import com.sqa.pco.constantes.Constantes;
import com.sqa.pco.procesosApi.ConsumoAPI;
import com.sqa.pco.procesosExcel.LeerExcel;
import com.sqa.pco.utilidades.Utilidades;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ActualizacionTest {

	public static WebDriver driver;

	@Given("^Main configuration$")
	public void mainConfiguration() throws Throwable {
		Configuracion configuracion = new Configuracion();
		System.setProperty(Constantes.WEBDRIVER, configuracion.leerArchivo(Constantes.RUTADRIVER).getMensaje());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(Constantes.URLPORTAL);
	}

	@When("^user navigates to Login Page$")
	public void user_navigates_to_Login_Page() throws Throwable {
		Utilidades utilidades = new Utilidades();
		int codigo = utilidades.loginPortal(driver).getCodigo();
		Assert.assertEquals(Integer.parseInt(Constantes.CODIGOEXITOSO), codigo);
	}

	@When("^user update data$")
	public void user_Update_Data() throws Throwable {
		Utilidades utilidades = new Utilidades();
		LeerExcel leerExcel = new LeerExcel();
		TreeMap<String, String> datos = new TreeMap<String, String>();
		datos = leerExcel.leerDataDriven();
		int codigo = utilidades.actualizacion(driver, datos).getCodigo();
		Assert.assertEquals(Integer.parseInt(Constantes.CODIGOEXITOSO), codigo);
	}

	@When("^user logout$")
	public void logout() throws InterruptedException {
		Utilidades utilidades = new Utilidades();
		int codigo = utilidades.logout(driver).getCodigo();
		Assert.assertEquals(Integer.parseInt(Constantes.CODIGOEXITOSO), codigo);
	}

	@And("^user close window$")
	public void Close() throws InterruptedException {
		Utilidades utilidades = new Utilidades();
		int codigo = utilidades.cerrarChome(driver).getCodigo();
		Assert.assertEquals(Integer.parseInt(Constantes.CODIGOEXITOSO), codigo);
	}

	@Then("^user validate on API$")
	public void user_validate_on_API()
			throws InterruptedException, IOException, OAuthSystemException, OAuthProblemException {
		LeerExcel leerExcel = new LeerExcel();
		ConsumoAPI consumoAPI = new ConsumoAPI();
		TreeMap<String, String> datos = new TreeMap<String, String>();
		datos = leerExcel.leerDataDriven();
		int codigo = consumoAPI.getAccountBalance(datos).getCodigo();
		Assert.assertEquals(Integer.parseInt(Constantes.CODIGOEXITOSO), codigo);
		if (codigo == 0) {
			try {
				Thread.sleep(3000);
				Runtime.getRuntime().exec("cmd /c start "
						+ "C://Users//Usuario//eclipse-workspace//ActualizaciondeDatos//target//cucumber-reports//report.html");
			} catch (IOException e) {
				System.out.println(e.getMessage());

			}
		}

	}

}
