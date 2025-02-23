package uiAutomation.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import uiAutomation.config.Environment;
import uiAutomation.pageObject.LogoutPage;

public class Logout {
    WebDriver driver = Environment.getInstance().getDriver();
    LogoutPage logoutPage = new LogoutPage(driver);

    @And("user click logout")
    public void userClickLogout() {
        logoutPage.clickLogOut();
    }

    @Then("user successfully logout")
    public void userSuccessfullyLogout() {
        String logoutAssert = logoutPage.validateLogOut();
        Assert.assertEquals("Log in",logoutAssert);
    }
}
