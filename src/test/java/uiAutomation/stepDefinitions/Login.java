package uiAutomation.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import uiAutomation.config.Environment;
import uiAutomation.pageObject.LoginPage;

import java.time.Duration;

public class Login {
    //Step Definitions for Login
    WebDriver driver = Environment.getInstance().getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @When("user click log in")
    public void userClickLogin() {
        loginPage.userClickLogin();
    }

    @And("user input (.*) as usernameLogin$")
    public void userInputUsernameloginAsUsername(String usernameLogin) {
        loginPage.inputUsernameLogin(usernameLogin);
    }

    @And("user input (.*) as passwordLogin$")
    public void userInputPasswordloginAsPassword(String passwordLogin) {
        loginPage.inputPasswordLogin(passwordLogin);
    }

    @And("user click log in button")
    public void userClickLogInButton() {
        loginPage.clickLoginButton();
    }

    @Then("user can see welcome with username")
    public void userCanSeeWelcomeWithUsername() {
        String nameOfUserAssert = loginPage.getNameOfUser();
        Assert.assertEquals("Welcome shevanr", nameOfUserAssert);

    }
}
