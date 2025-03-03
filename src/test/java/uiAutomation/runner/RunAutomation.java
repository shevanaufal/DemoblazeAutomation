package uiAutomation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/uiAutomation/features",
        glue = "uiAutomation.stepDefinitions",
        plugin = {"pretty","html:reports/DemoblazeCucumber.html","json:reports/cucumber.json"},
        tags = "@web"
)

public class RunAutomation {
    //This is for running UI Automation

}
