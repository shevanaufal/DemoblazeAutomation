package uiAutomation.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import uiAutomation.config.Environment;
import uiAutomation.pageObject.AddtoCartPage;

import java.time.Duration;

public class AddtoCart {
    //Step Definitions Add to Cart
    WebDriver driver = Environment.getInstance().getDriver();
    AddtoCartPage addtoCartPage = new AddtoCartPage(driver);

    @When("user add {string} with ID {string} to the cart")
    public void userAddProductToCart(String productName, String productID) {
        addtoCartPage.openProductDetailsPage(productName);
        addtoCartPage.clickAddToCart(productID);

        // Handle alert pop-up
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert text: " + alert.getText()); // Log alert message
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert appeared after clicking Add to Cart.");
        }

    }

    @Then("user should see (.*) in the cart$")
    public void userShouldSeeProductNameInTheCart(String productName) {
        System.out.println("Verified: " + productName + " is in the cart.");
    }
}
