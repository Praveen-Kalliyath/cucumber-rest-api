/**
	 * @author Praveen Kalliyath
	 */
package runner;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import testutils.Data;
import testutils.PropertiesUtil;
import testutils.Report;

//Uncomment below lines if JUnit is been used instead of TestNG
//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//@RunWith(Cucumber.class)
@CucumberOptions(tags = ("@GET or @PUT or @POST or @DELETE or @Regression"), glue = { "stepdefinition" }, features = {
		"resource/features/" }, plugin = { "runner.MyTestListener", "pretty",
				"json:target/cucumber-json/cucumber.json" })
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
	@BeforeClass
	public void setup() {
		new PropertiesUtil();
		new Report();
		Report.createReport();
	}

	@AfterClass
	public void completed() {
		System.out.println("\n\nCOMPLETED EXECUTION\n\n");
		Report.flushReport();
	}

}
