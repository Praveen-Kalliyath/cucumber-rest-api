package runner;

//import org.apache.commons.lang.StringUtils;
//import cucumber.api.Argument;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepStarted;
import testutils.Report;

public class MyTestListener implements ConcurrentEventListener {
	public static String step;
	public String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestStepStarted.class, new EventHandler<TestStepStarted>() {
			@Override
			public void receive(TestStepStarted event) {
				if (event.getTestStep() instanceof PickleStepTestStep) {
					final PickleStepTestStep ev = (PickleStepTestStep) event.getTestStep();
					// final String args = StringUtils
					// .join(ev.getDefinitionArgument().stream().map(Argument::getValue).toArray(),
					// ",");
					// String testDescription = ev.getStepText() + " : " +
					// ev.getStepLocation();
					// if (StringUtils.isNotBlank(args)) {
					// testDescription += (" : args = (" + args + ")");
					// }
					System.out.println("STARTING STEP: " + ev.getStep().getKeyword() + " : " + ev.getStep().getText());
					setDescription(ev.getStep().getText());
					Report.createScenarioStep(ev.getStep().getKeyword(), ev.getStep().getText());
					// Reporter.addStepLog("Pass");
					// Reporter.addScenarioLog("This is Scenario");
				}
			}
		});

		publisher.registerHandlerFor(TestStepStarted.class, new EventHandler<TestStepStarted>() {
			@Override
			public void receive(TestStepStarted event) {
				if (event.getTestStep() instanceof PickleStepTestStep) {
					PickleStepTestStep ev = (PickleStepTestStep) event.getTestStep();
					System.out.println("FINISHED STEP: " + ev.getStep().getKeyword() + " : " + ev.getStep().getText());

				}
			}
		});

	}

}