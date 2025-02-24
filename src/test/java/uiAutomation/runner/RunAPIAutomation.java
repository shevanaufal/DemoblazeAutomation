package uiAutomation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/apiAutomation/features",
        glue = "apiAutomation.stepDefinitions",
        plugin = {"pretty", "html:reports/APITests.html", "json:reports/cucumber.json"},
        tags = "@api"
)
public class RunAPIAutomation {
}
