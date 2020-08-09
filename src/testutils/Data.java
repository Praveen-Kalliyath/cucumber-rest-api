/**
	 * @author Praveen Kalliyath
	 */
package testutils;

public interface Data {

	public final static String RESOURCE_FOLDER_PATH = System.getProperty("user.dir") + "/resource";
	public final static String ENVCONFIG_FOLDER_PATH = RESOURCE_FOLDER_PATH + "/envConfig/";
	public final static String JSON_FOLDER_PATH = RESOURCE_FOLDER_PATH + "/jsons/";
	public final static String FEATURE_FOLDER_PATH = RESOURCE_FOLDER_PATH + "/features/";
	public final static String REPORT_FOLDER_PATH = "./report/";

	public static final String ENV_NAME = System.getProperty("envName", "train");

}
