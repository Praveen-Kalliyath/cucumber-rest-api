/**
	 * @author Praveen Kalliyath
	 */
package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

public class PropertiesUtil implements Data {

	private static Properties properties;
	public static boolean propertiesLoaded = false;

	public PropertiesUtil() {
		if (!propertiesLoaded) {
			loadConfig();
			propertiesLoaded = true;
		}
	}

	/**
	 * Loading Properties Files for Framework & Application
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public void loadConfig() {
		try {
			// FRAMEWORK CONFIG FILE
			Log.info("Loading Framework Configuration");
			FileInputStream frameworkFile = new FileInputStream(ENVCONFIG_FOLDER_PATH + ENV_NAME + ".properties");
			properties = new Properties();
			properties.load(frameworkFile);
			Log.info("Loaded Framework Properties File");
			Log.info("Configuration File Data Map: " + properties);
		} catch (FileNotFoundException e) {
			Log.error("Configuration file not found. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.error("Failed to load configuration file. Error Message:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to write 'Key' & 'Value' to Properties Files
	 * 
	 * @param key
	 * 
	 * @param value
	 * 
	 * @exception ConfigurationException
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public void writeToConfigFile(String key, String value) {
		try {
			File file = new File(ENVCONFIG_FOLDER_PATH + ENV_NAME + ".properties");
			PropertiesConfiguration configuration = new PropertiesConfiguration();
			PropertiesConfigurationLayout configurationLayout = new PropertiesConfigurationLayout(configuration);
			configurationLayout.load(new InputStreamReader(new FileInputStream(file)));
			configuration.setProperty(key, value);
			configurationLayout.save(new FileWriter(file));
			Log.info("Saved data to properties file.");
			Log.info("Key '[" + key + "]' : Value '[" + value + "]'");
		} catch (ConfigurationException e) {
			Log.error("Exception Occured. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			Log.error("Configuration file not found. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.error("Failed to write data to configuration file. Error Message:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to get property value
	 * 
	 * @param key
	 * 
	 * @return Property Value
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
