package uiAutomation.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import uiAutomation.config.Environment;

public class Hooks {
    //Declare Hooks
    private WebDriver driver;

    @Before
    public void before(){
        if(Environment.getInstance().getDriver() == null){
            Environment.getInstance();
        }
        driver = Environment.getInstance().getDriver();
    }

    @After
    public void after(Scenario scenario){
        if (driver != null){
            if (scenario.isFailed()) {
                driver.manage().deleteAllCookies(); //Clear cookies if test fails
            }
            Environment.getInstance().quitDriver();
        }
    }
}
