/**
	 * @author Praveen Kalliyath
	 */
package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import library.JsonPlaceHolderService;
import testutils.Report;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import io.cucumber.datatable.DataTable;

public class GmailStepDef {

	private JsonPlaceHolderService jsonPlaceHolder;
	// private List<Map<Object, Object>> dataMap;

	public GmailStepDef() {
		jsonPlaceHolder = new JsonPlaceHolderService();
	}

	@Given("User navigates to the gmail home page")
	public void user_navigates_to_the_gmail_home_page() {
		
		
	}

	@Given("I login using the credentials")
	public void i_login_using_the_credentials() {
		
		
	}

	@When("I validate the login details")
	public void i_validate_the_login_details() {
		
		
	}

	@And("I close the browser")
	public void i_close_the_browser() {
		
		
	}

	@When("I validate home screen details")
	public void i_validate_home_screen_details() {
		
		
	}

	@Then("I click on logout button")
	public void i_click_on_logout_button() {
		
		
	}

	@Then("I validate user landed on the login page")
	public void i_validate_user_landed_on_the_login_page() {
		
		
	}
}
