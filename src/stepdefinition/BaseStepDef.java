/**
	 * @author Praveen Kalliyath
	 */
package stepdefinition;

import java.util.ArrayList;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import testutils.PropertiesUtil;
import testutils.Report;

public class BaseStepDef {

	private Scenario scenario;
	private static ArrayList<String> featureList = new ArrayList<String>();

	public BaseStepDef() {
		if (!PropertiesUtil.propertiesLoaded) {
			new PropertiesUtil();
		}

	}

	@Before
	public void initialize(Scenario scenario) {
		setScenario(scenario);
		String[] feature = getScenario().getId().split("/");
		String featureName = feature[(feature.length - 1)].substring(0, feature[(feature.length - 1)].indexOf(":"));
		if (featureList.size() == 0) {
			System.out.println("\n\n FEATURE: " + getScenario().getName() + "\n\n");
			featureList.add(featureName);
			Report.createFeature(featureName);
		} else {
			System.out.println("\n\n FEATURE: " + getScenario().getName() + "\n\n");
			System.out.println(featureList);
			if (!featureList.contains(featureName)) {
				featureList.add(featureName);
				Report.createFeature(featureName);
			}
		}
		Report.createScenario(getScenario().getName());
	}

	@BeforeStep
	public void BeforeEveryStep() {

	}

	@AfterStep
	public void AfterEveryStep() {

	}

	@After
	public void tearDown() {
		System.out.println("\n\nCOMPLETED RUNNING SCENARIO: " + getScenario().getName() + "\n\n");
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
}
