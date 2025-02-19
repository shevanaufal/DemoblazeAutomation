package uiAutomation.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import uiAutomation.config.environment;

public class hooks {
    private WebDriver driver;

    @Before
    public void before(){
        driver = environment.getInstance().getDriver();
    }

    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()) {
            driver.manage().deleteAllCookies(); // Clear cookies if test fails
        }
        environment.getInstance().quitDriver();
    }
}
