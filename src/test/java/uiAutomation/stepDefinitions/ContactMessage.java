package uiAutomation.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import uiAutomation.config.Environment;
import uiAutomation.pageObject.ContactPage;

import java.time.Duration;

public class ContactMessage {
    //Step Definitions for Send Contact Message
    String contactEmailFaker;
    String contactNameFaker;
    String contactMessageFaker;

    WebDriver driver = Environment.getInstance().getDriver();
    ContactPage contactModal = new ContactPage(driver);

    @When("user click contact")
    public void userClickLogin() {
        contactModal.openContactModal();
    }

    @And("user input {string} as contact email")
    public void userInputContactEmail(String contactEmail) {
        if(contactEmail.equals("random")){
            Faker faker = new Faker();
            contactEmailFaker = faker.name().username() + "@demoblazemail.com";
        }else{
            contactEmailFaker = contactEmail;
        }
        contactModal.inputContactEmail(contactEmailFaker);
    }

    @And("user input {string} as contact name")
    public void userInputContactName(String contactName) {
        if(contactName.equals("random")){
            Faker faker = new Faker();
            contactNameFaker = faker.name().fullName();
        } else {
            contactNameFaker = contactName;
        }
        contactModal.inputContactName(contactNameFaker);
    }

    @And("user input {string} contact message")
    public void userInputContactMessage(String contactMessage) {
        if(contactMessage.equals("random")){
            Faker faker = new Faker();
            contactMessageFaker = faker.lorem().paragraph(2);
        } else {
            contactMessageFaker = contactMessage;
        }
        contactModal.inputContactMessage(contactMessageFaker);
    }

    @And("user click send message")
    public void userClickSendMessage() {
        contactModal.clickSendMessage();
    }

    @Then("will show success send message (.*) as success message$")
    public void willShowSuccessSendMessage(String successMessage) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoAlertPresentException.class);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualSuccesSendMessage = alert.getText();
            System.out.println("Thanks for the message!!" + actualSuccesSendMessage);
            Assert.assertEquals("Alert message does not match", successMessage, actualSuccesSendMessage);
            alert.accept();

        } catch (TimeoutException e){
            throw new RuntimeException("Alert did not appear within the expected time: " + e.getMessage());
        }
    }
}
