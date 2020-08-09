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

public class JsonPlaceHolderStepDef {

	private JsonPlaceHolderService jsonPlaceHolder;
	// private List<Map<Object, Object>> dataMap;

	public JsonPlaceHolderStepDef() {
		jsonPlaceHolder = new JsonPlaceHolderService();
	}

	@Given("Validate the number of users in the user list GET service url")
	public void validate_the_number_of_users_in_the_user_list_get_service_url() {
	}

	@When("I send the GET request")
	public void i_send_the_get_request() {
		jsonPlaceHolder.sendGetRequestToGetUserDetails();
	}

	@And("I validate reposnse status {int}")
	public void i_validate_reposnse_status(Integer statusCode) {
		jsonPlaceHolder.validateStatusCountForResponse(statusCode);
	}

	@And("I validate the count of users {int}")
	public void i_validate_the_count_of_users(Integer count) {
		jsonPlaceHolder.validateCountOfUsersIntheList(count);
	}

	@Given("I provide the PUT request url")
	public void i_provide_the_put_request_url() {

	}

	@Then("I user update the title value in json and send PUT request")
	public void i_user_update_the_title_value_in_json_and_send_put_request(DataTable dataTable) {
		jsonPlaceHolder.updateTitle(dataTable.asMaps(Object.class, Object.class));
	}

	@Given("I provide the POST request url")
	public void i_provide_the_post_request_url() {

	}

	@Then("I send the POST request with the payload")
	public void i_send_the_post_request_with_the_payload() {
		jsonPlaceHolder.sendPostRequestToGetUserDetails();
	}

	@Then("I validate title from reposnse body")
	public void i_validate_title_from_reposnse_body() {
		jsonPlaceHolder.validateTitleFromResponse();
	}

	@Given("I provide the Delete request url")
	public void i_provide_the_delete_request_url() {

	}

	@Then("I send the DELETE request")
	public void i_send_the_delete_request() {
		jsonPlaceHolder.sendDeleteRequestAndValidateResponse();
	}

	@Then("I validate response body is empty")
	public void i_validate_reposnse_body_is_empty() {
		jsonPlaceHolder.validateResponseIsEmptyAfterDeleteRequest();
	}
}
