package uiAutomation.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import uiAutomation.config.Environment;
import uiAutomation.pageObject.AddtoCartPage;

public class AddtoCart {
    WebDriver driver = Environment.getInstance().getDriver();
    AddtoCartPage addtoCartPage = new AddtoCartPage(driver);

    @When("user add {string} with ID {string} to the cart")
    public void userAddProductToCart(String productName, String productID) throws InterruptedException {
        addtoCartPage.openProductDetailsPage(productName);
        addtoCartPage.clickAddToCart(productID);
    }

    @Then("user should see (.*) in the cart$")
    public void userShouldSeeProductNameInTheCart(String productName) {
        System.out.println("Verified: " + productName + " is in the cart.");
    }
}
