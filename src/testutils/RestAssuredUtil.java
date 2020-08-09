/**
	 * @author Praveen Kalliyath
	 */
package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static com.jayway.jsonpath.JsonPath.using;
import static io.restassured.RestAssured.given;

public class RestAssuredUtil implements Data {
	private static RequestSpecBuilder builder;
	private static RequestSpecification specification;
	private static Response response;

	private static final String DEFAULT_CONTENT_TYPE = "application/json";

	public RestAssuredUtil() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
		RestAssured.config = RestAssuredConfig.config()
				.decoderConfig(new DecoderConfig().contentDecoders(DecoderConfig.ContentDecoder.DEFLATE));
	}

	/**
	 * ADD HEADERS
	 */
	private static void addHeader() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", DEFAULT_CONTENT_TYPE);
		// headers.put("Accept-Encoding", "gzip");
		builder.addHeaders(headers);
	}

	/**
	 * ADD PARAMS
	 * 
	 * @param params
	 */
	private static void addParams(Map<String, String> params) {
		builder.addParams(params);
	}

	/**
	 * ADD PATHPARAMS
	 * 
	 * @param pathParams
	 */
	private static void addPathParams(Map<String, String> pathParams) {
		builder.addPathParams(pathParams);
	}

	/**
	 * CREATE REQUEST SPECIFICATION
	 * 
	 * @return
	 */
	public static RequestSpecification getRequestSpecification() {
		builder = new RequestSpecBuilder();
		addHeader();
		builder.setBaseUri(PropertiesUtil.getProperty("API_BASE_URI"));
		specification = builder.build();

		return specification;
	}

	/**
	 * CREATE REQUEST SPECIFICATION
	 * 
	 * @param params
	 * @return
	 */
	public static RequestSpecification getRequestSpecification(Map<String, String> params) {
		builder = new RequestSpecBuilder();
		addHeader();
		addParams(params);
		builder.setBaseUri(PropertiesUtil.getProperty("API_BASE_URI"));
		specification = builder.build();

		return specification;
	}

	/**
	 * CREATE REQUEST SPECIFICATION
	 * 
	 * @param params
	 * @param pathParams
	 * @return
	 */
	public static RequestSpecification getRequestSpecification(Map<String, String> params,
			Map<String, String> pathParams) {
		builder = new RequestSpecBuilder();
		addHeader();
		addParams(params);
		addPathParams(pathParams);
		builder.setBaseUri(PropertiesUtil.getProperty("API_BASE_URI"));
		specification = builder.build();

		return specification;
	}

	/**
	 * CREATE REQUEST SPECIFICATION
	 * 
	 * @param params
	 * @param pathParams
	 * @param baseURI
	 * @return
	 */
	public static RequestSpecification getRequestSpecification(Map<String, String> params,
			Map<String, String> pathParams, String baseURI) {
		builder = new RequestSpecBuilder();
		addHeader();
		addParams(params);
		addPathParams(pathParams);
		builder.setBaseUri(PropertiesUtil.getProperty(baseURI));
		specification = builder.build();

		return specification;
	}

	/**
	 * SEND GET REQUEST AND RETURN RESPONSE
	 * 
	 * @param basePath
	 */
	public static void sendGETRequestAndReturnResponse(String basePath) {
		setResponse(given().spec(getRequestSpecification())
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).log().all().when()
				.get(PropertiesUtil.getProperty(basePath)));
	}

	/**
	 * SEND GET REQUEST AND RETURN RESPONSE
	 * 
	 * @param basePath
	 */
	public static void sendGETRequestAndReturnResponseUsingAuth(String basePath) {
		setResponse(given().auth().preemptive().basic("username", "password").spec(getRequestSpecification())
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).log().all().when()
				.get(PropertiesUtil.getProperty(basePath)));
	}

	/**
	 * SEND PUT REQUEST AND RETURN RESPONSE
	 * 
	 * @param basePath
	 * @param payload
	 */
	public static void sendPUTRequestAndReturnResponse(String basePath, String payload) {
		setResponse(given().spec(getRequestSpecification())
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).log().all()
				.body(payload).when().put(PropertiesUtil.getProperty(basePath)));
	}

	/**
	 * SEND POST REQUEST AND RETURN RESPONSE
	 * 
	 * @param basePath
	 * @param payload
	 */
	public static void sendPOSTRequestAndReturnResponse(String basePath, String payload) {
		setResponse(given().spec(getRequestSpecification())
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).log().all()
				.body(payload).when().post(PropertiesUtil.getProperty(basePath)));
	}

	/**
	 * SEND DELETE REQUEST AND RETURN RESPONSE
	 * 
	 * @param basePath
	 */
	public static void sendDELETERequestAndReturnResponse(String basePath) {
		setResponse(given().spec(getRequestSpecification())
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).log().all().when()
				.delete(PropertiesUtil.getProperty(basePath)));
	}

	/**
	 * Getter Method For Reponse
	 * 
	 * @return
	 */
	public static Response getResponse() {
		return response;
	}

	/**
	 * Setter Method For Response
	 * 
	 * @param response
	 */
	public static void setResponse(Response resquestResponse) {
		response = resquestResponse;
	}

	/**
	 * GET JSON FROM FILE
	 * 
	 * @param jsonFilePath
	 * @return
	 */
	public static DocumentContext getJsonFromFile(String jsonFilePath) {
		FileInputStream json = null;
		try {
			json = new FileInputStream(new File(JSON_FOLDER_PATH + jsonFilePath + ".json"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
				.mappingProvider(new JacksonMappingProvider()).build();
		return using(configuration).parse(json);
	}

	/**
	 * GET JSON FROM PAYLOAD
	 * 
	 * @param payload
	 * @return
	 */
	public static DocumentContext getJsonFromPayLoad(String payload) {
		Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
				.mappingProvider(new JacksonMappingProvider()).build();
		return using(configuration).parse(payload);
	}
}
