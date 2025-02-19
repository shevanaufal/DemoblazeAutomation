package uiAutomation.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import uiAutomation.config.environment;

import java.time.Duration;

public class signUp {
    String usernameFaker;
    String passwordFaker;

    WebDriver driver = environment.getInstance().getDriver();
    @Given("demoblaze homepage")
    public void demoblaze_homepage(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String homeAssert = driver.findElement(By.xpath("//a[@class='navbar-brand']")).getText();
        Assert.assertEquals(homeAssert, "PRODUCT STORE");
    }

    @When("user click sign up")
    public void user_click_sign_up() {
        driver.findElement(By.xpath("//a[@id='signin2']")).click();
    }

    @And("user input {string} as username")
    public void user_input_username_as_username(String username) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)));
        if(username.equals("random")){
            Faker faker = new Faker();
            usernameFaker = faker.name().username();
        }else{
            usernameFaker = username;
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='sign-username']"))).sendKeys(usernameFaker);
//        System.out.println("Username Random" + usernameFaker);
    }

    @And("user input {string} as password")
    public void user_input_password_as_password(String password) {
        if(password.equals("random")){
            Faker faker = new Faker();
            passwordFaker = faker.internet().password();
        }else{
            passwordFaker = password;
        }
        driver.findElement(By.xpath("//input[@id='sign-password']")).sendKeys(passwordFaker);
//        System.out.println("Password Random" + passwordFaker);
    }

    @And("user click sign up button")
    public void user_click_sign_up_button() {
        driver.findElement(By.xpath("//button[@onclick='register()']")).click();
    }

    @Then("will show success sign up (.*) as expected message$")
    public void will_show_success_sign_up_expected_message(String expectedMessage) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)));

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualMessage = alert.getText();
            System.out.println("Sign up successful." + actualMessage);
            Assert.assertEquals("Alert message does not match", expectedMessage, actualMessage);
            alert.accept();

        } catch (TimeoutException e){
            throw new RuntimeException("Alert did not appear within the expected time: " + e.getMessage());
        }
    }


}
