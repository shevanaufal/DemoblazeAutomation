package uiAutomation.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import uiAutomation.config.Environment;
import uiAutomation.pageObject.CheckoutPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CheckoutSteps {

    //Step Definitions Checkout
    WebDriver driver = Environment.getInstance().getDriver();
    CheckoutPage checkoutPage = new CheckoutPage(driver);

    @And("user goes to the cart")
    public void userGoesToTheCart() {
        checkoutPage.openCartPage();
    }

    @And("user completes checkout with random details")
    public void userCompletesCheckout() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String country = faker.country().name();
        String city = faker.address().city();
        String card = faker.finance().creditCard();
        String month = String.valueOf(faker.number().numberBetween(1, 12));
        String year = String.valueOf(faker.number().numberBetween(2025, 2035));

        checkoutPage.placeOrder(name, country, city, card, month, year);

    }

    @And("user click purchase button")
    public void userClickPurchaseButton() {
        checkoutPage.clickPurchaseButton();
    }

    @Then("order should be successful")
    public void orderShouldBeSuccessful() {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver) // Use the WebDriver instance
                    .withTimeout(Duration.ofSeconds(15))  // Max wait time
                    .pollingEvery(Duration.ofMillis(500)) // Check every 500ms
                    .ignoring(NoSuchElementException.class);

            boolean isSuccess = wait.until(d -> checkoutPage.isOrderSuccessful());

            assertTrue("Order was not successful!", isSuccess);
        } catch (Exception e) {
            System.out.println("Error: Order success message not found. " + e.getMessage());
            fail("Order was not successful!");
        }
    }

    @And("user confirm modal success purchase")
    public void userConfirmModalSuccessPurchase() {
        try {
            checkoutPage.handlePurchaseConfirmationFlow();
        } catch (Exception e) {
            System.err.println("CRITICAL: Failed during the purchase confirmation flow: " + e.getMessage());
            fail("Failed to handle purchase confirmation modals: " + e.getMessage());
        }
    }

}
