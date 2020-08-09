/**
	 * @author Praveen Kalliyath
	 */
package testutils;

import java.net.InetAddress;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Report implements Data {
	private static ExtentReports extentReports;
	private static ExtentTest extentFeature;
	private static ExtentTest extentScenario;
	private static ExtentTest extentStep;
	private static boolean failurePresent = false;
	private static String reportName;
	public static boolean setupComplete = false;

	/**
	 * Method To Create Extent Report
	 */
	public static void createReport() {
		Log.info("Creating Execution Report");
		Log.info("Creating Report In Location: " + REPORT_FOLDER_PATH);
		File dir = new File(REPORT_FOLDER_PATH);
		if (!dir.exists())
			dir.mkdir();
		try {
			FileUtils.cleanDirectory(new File(REPORT_FOLDER_PATH));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// Log.info("Creating Report In Location: " +
		// System.getProperty("user.home") + "/Report.html");
		if (extentReports == null) {
			reportName = "Report_" + CommonUtil.returnToday("MMM_dd_yyyy_hh_mm_ss") + ".html";
			ExtentHtmlReporter aventReporter = new ExtentHtmlReporter(REPORT_FOLDER_PATH + reportName);
			extentReports = new ExtentReports();
			extentReports.attachReporter(aventReporter);

			try {
				extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
				extentReports.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
				extentReports.setSystemInfo("IP Address", InetAddress.getLocalHost().getHostAddress());
				extentReports.setSystemInfo("OS Version", System.getProperty("os.name"));
				extentReports.setSystemInfo("Java Version", System.getProperty("user.java"));
			} catch (Exception e) {
				Log.error("Failed set system information to the report. Error Message: " + e.getMessage());
			}
		}
		setupComplete = true;
	}

	/**
	 * Method To Create Extent Test
	 * 
	 * @return extentFeature
	 */
	public static ExtentTest createFeature(String featureName) {
		if (setupComplete) {
			Log.info("Created Report Section: '" + featureName + "'");
			extentFeature = extentReports.createTest(Feature.class, featureName);
		}
		return extentFeature;
	}

	/**
	 * Method To Create Extent Test
	 * 
	 * @return extentScenario
	 */
	public static ExtentTest createScenario(String scenaioName) {
		if (setupComplete) {
			Log.info("Created Test Section Node: '" + scenaioName + "'");
			extentScenario = extentFeature.createNode(scenaioName);
		}
		return extentScenario;
	}

	/**
	 * Method To Create Extent Test
	 * 
	 * @return extentStep
	 */
	public static ExtentTest createScenarioStep(String step) {
		if (setupComplete) {
			Log.info("Created Scenario Step Node: '" + step + "'");
			extentStep = extentScenario.createNode(step);
		}
		return extentStep;
	}

	/**
	 * Method To Create Extent Test
	 * 
	 * @return extentStep
	 */
	public static ExtentTest createScenarioStep(String gherinKeyword, String step) {
		if (setupComplete) {
			Log.info("Created Test Section Node: '" + step + "'");
			try {
				extentStep = extentScenario.createNode(new GherkinKeyword(gherinKeyword), gherinKeyword + " - " + step);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return extentStep;
	}

	/**
	 * Method To Flush & Save Extent Report
	 */
	public static void flushReport() {
		if (setupComplete) {
			if (extentReports != null) {
				extentReports.flush();
				Log.info("Flushed Report.");
				Log.info("Saved Report In Location: " + REPORT_FOLDER_PATH + reportName);
			}
		}
	}

	/**
	 * Method To Log Link Details
	 * 
	 * @param linkName
	 * 
	 * @param path
	 */
	public static void link(String linkName, String link) {
		Log.info(linkName + " : " + link);
		if (setupComplete) {
			if (extentStep != null) {
				extentStep.info("Please refer the link provided: <a href=" + link + ">" + linkName + "</a>");
			}
		}
	}

	/**
	 * Method To Log Info
	 * 
	 * @param message
	 */
	public static void info(String message) {
		Log.info(message);
		if (setupComplete) {
			if (extentStep != null) {
				extentStep.info(message);
			}
		}
	}

	/**
	 * Method To Log Pass
	 * 
	 * @param message
	 */
	public static void pass(String message) {
		Log.info(message);
		if (setupComplete) {
			if (extentStep != null) {
				extentStep.pass(message);
			}
		}
	}

	/**
	 * Method To Log Fail
	 * 
	 * @param message
	 */
	public static void fail(String message) {
		Log.error(message);
		if (setupComplete) {
			failurePresent = true;
			if (extentStep != null) {
				extentStep.fail(message);
			}
		}
	}

	/**
	 * Method To Log Warning
	 * 
	 * @param message
	 */
	public static void warn(String message) {
		Log.warn(message);
		if (setupComplete) {
			if (extentStep != null) {
				extentStep.warning(message);
			}
		}
	}

	/**
	 * Method To Log Exception
	 * 
	 * @param message
	 */
	public static void exception(Exception e) {
		Log.error(e.getMessage());
		if (setupComplete) {
			failurePresent = true;
			if (extentStep != null) {
				extentStep.fail(e);
			}
		}
	}

	/**
	 * Method To Log Skip
	 * 
	 * @param message
	 */
	public static void skip(String message) {
		Log.info("Skipped: " + message);
		if (setupComplete) {
			if (extentStep != null) {
				extentStep.skip(message);
			}
		}
	}

	/**
	 * Method To Log Using Status
	 * 
	 * @param message
	 */
	public static void log(Status status, String message) {
		Log.info(status + " : " + message);
		if (setupComplete) {
			if (extentStep != null) {
				extentStep.log(status, message);
			}
		}
	}

	/**
	 * Method To Log Label
	 * 
	 * @param message
	 */
	public static void label(String message) {
		Log.info("LABEL: " + message);
		if (setupComplete) {
			Markup label = MarkupHelper.createLabel(message, ExtentColor.BLUE);
			if (extentStep != null) {
				extentStep.info(label);
			}
		}
	}

	/**
	 * Method To Get Status
	 * 
	 * @return status
	 */
	public static String status() {
		String status = "";
		if (setupComplete) {
			if (extentStep != null) {
				status = extentStep.getStatus().toString();
			}
		}
		return status;
	}

	public static boolean failurePresent() {
		return failurePresent;
	}

}
