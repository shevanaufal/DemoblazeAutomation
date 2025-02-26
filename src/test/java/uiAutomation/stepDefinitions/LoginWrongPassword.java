package uiAutomation.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import uiAutomation.config.Environment;

import java.time.Duration;

public class LoginWrongPassword {
    WebDriver driver = Environment.getInstance().getDriver();

    @Then("user will get (.*) as wrong password message$")
    public void willGetWrongPasswordMessage(String wrongPasswordMessage) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoAlertPresentException.class);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualWrongPasswordMessage = alert.getText();
            System.out.println("Wrong Password." + wrongPasswordMessage);
            Assert.assertEquals("Alert message does not match", wrongPasswordMessage, actualWrongPasswordMessage);
            alert.accept();

        } catch (TimeoutException e){
            throw new RuntimeException("Alert did not appear within the expected time: " + e.getMessage());
        }
    }
}
