package uiAutomation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/uiAutomation/features",
        glue = "uiAutomation/stepDefinitions",
        plugin = {"pretty","html:reports/DemoblazeCucumber.html","json:reports/cucumber.json"},
        tags = "@TDD"
)

public class runAutomation {

}
