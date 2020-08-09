/**
	 * @author Praveen Kalliyath
	 */
package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;

import testutils.CommonUtil;
import testutils.Report;
import testutils.RestAssuredUtil;
import static com.jayway.jsonpath.JsonPath.parse;

public class JsonPlaceHolderService {

	public void sendGetRequestToGetUserDetails() {
		RestAssuredUtil.sendGETRequestAndReturnResponse("GET_RESOURCE_BASEPATH");
		Report.info("Response : " + RestAssuredUtil.getResponse().getBody().asString());
		Report.info("Response Code : " + RestAssuredUtil.getResponse().getStatusCode());
	}

	public void validateCountOfUsersIntheList(int count) {
		Report.info("Response : " + RestAssuredUtil.getResponse().getBody().asString());
		String json = RestAssuredUtil.getResponse().getBody().asString();
		ArrayList<Integer> userCount = parse(json).read("$.[*].id");
		Report.info("User Count From Response: " + userCount.size());
		CommonUtil.compareActualWithExpected("User Count ", userCount.size(), count);
	}

	public void validateStatusCountForResponse(int statusCode) {
		Report.info("Service Response Code : " + RestAssuredUtil.getResponse().getStatusCode());
		CommonUtil.compareActualWithExpected("Response Code", RestAssuredUtil.getResponse().getStatusCode(),
				statusCode);
	}

	public void sendPostRequestToGetUserDetails() {
		DocumentContext json = RestAssuredUtil.getJsonFromFile("Test");
		Report.info("JSON : " + json.jsonString());

		RestAssuredUtil.sendPOSTRequestAndReturnResponse("POST_RESOURCE_BASEPATH", json.jsonString());
		Report.info("Response : " + RestAssuredUtil.getResponse().getBody().asString());
	}

	public void validateTitleFromResponse() {
		String title = parse(RestAssuredUtil.getResponse().getBody().asString()).read("$.title");
		Report.info("Title is: " + title);
	}

	public void sendPutRequestAndValidateResponse() {
		DocumentContext json = RestAssuredUtil.getJsonFromFile("Test");
		Report.info("JSON : " + json.jsonString());

		RestAssuredUtil.sendPUTRequestAndReturnResponse("PUT_RESOURCE_BASEPATH", json.jsonString());
		Report.info("Response : " + RestAssuredUtil.getResponse().getBody().asString());
		Report.info("Response Code : " + RestAssuredUtil.getResponse().getStatusCode());
	}

	public void sendDeleteRequestAndValidateResponse() {
		RestAssuredUtil.sendDELETERequestAndReturnResponse("DELETE_RESOURCE_BASEPATH");
		Report.info("Response : " + RestAssuredUtil.getResponse().getBody().asString());
	}

	public void validateResponseIsEmptyAfterDeleteRequest() {
		String response = RestAssuredUtil.getResponse().getBody().asString();
		CommonUtil.compareActualWithExpected("REST RESPONSE", "{}", response);
	}

	public void updateTitle(List<Map<Object, Object>> dataMap) {
		DocumentContext json = RestAssuredUtil.getJsonFromFile("Test");
		Report.info("JSON : " + json.jsonString());

		for (int i = 0; i < dataMap.size(); i++) {
			Report.info("Map Key Value:" + dataMap.get(i).get("title"));

			json.set("title", dataMap.get(i).get("title"));
			RestAssuredUtil.sendPUTRequestAndReturnResponse("PUT_RESOURCE_BASEPATH", json.jsonString());
			Report.info("Updated Response With New Title : " + RestAssuredUtil.getResponse().getBody().asString());

		}

	}

}
