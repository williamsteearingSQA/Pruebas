package runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = "seleniumGlueCode", plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, monochrome = true, tags = {
				"@End2End" })

public class ActualizacionRunner {

	@AfterClass
	public static void teardown() throws InterruptedException {
		Reporter.loadXMLConfig(new File("src/test/java/report.xml"));
		Reporter.setSystemInfo("User", System.getProperty("William Steearing"));
		Reporter.setSystemInfo("OS", "Windows");
		Reporter.setTestRunnerOutput("Update data");

	}

}
