package uiAutomation.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By cartLink = By.id("cartur"); // Cart button
    private final By placeOrderButton = By.xpath("//button[contains(text(),'Place Order')]");
    private final By nameField = By.id("name");
    private final By countryField = By.id("country");
    private final By cityField = By.id("city");
    private final By creditCardField = By.id("card");
    private final By monthField = By.id("month");
    private final By yearField = By.id("year");
    private final By purchaseButton = By.xpath("//button[contains(text(),'Purchase')]");
    private final By successMessage = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void openCartPage() {
        click(cartLink);
    }

    public void placeOrder(String name, String country, String city, String card, String month, String year) {
        click(placeOrderButton);
        type(nameField, name);
        type(countryField, country);
        type(cityField, city);
        type(creditCardField, card);
        type(monthField, month);
        type(yearField, year);
        click(purchaseButton);
    }

    public boolean isOrderSuccessful() {
        return isDisplayed(successMessage);
    }
}
