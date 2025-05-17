package apiAutomation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/apiAutomation/features",
        glue = "apiAutomation.stepDefinitions",
        plugin = {
                "pretty",
                "html:reports/api/ApiCucumberReport.html",
                "json:reports/api/cucumberAPI.json"
        },
        tags = "@api"
)
public class RunAPIAutomation {
    // This is for running API Automation
}