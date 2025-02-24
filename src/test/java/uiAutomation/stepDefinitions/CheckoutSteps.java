package uiAutomation.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import uiAutomation.config.Environment;
import uiAutomation.pageObject.CheckoutPage;

import static org.junit.Assert.assertTrue;

public class CheckoutSteps {
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

    @Then("order should be successful")
    public void orderShouldBeSuccessful() {
        assertTrue(checkoutPage.isOrderSuccessful());
    }
}
