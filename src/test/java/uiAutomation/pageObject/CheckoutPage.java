package uiAutomation.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    //Checkout Page to Save Xpath Object

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
    private final By sweetAlertOkButton = By.xpath("//button[contains(@class, 'confirm') and text()='OK']");
    private final By sweetAlertLocator = By.xpath("//div[contains(@class, 'sweet-alert') and contains(@class, 'visible')]");
    private final By orderModalLocator = By.id("orderModal");
    private final By orderModalCloseButton = By.xpath("//div[@id='orderModal']//button[text()='Close']");


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

    }

    public void clickPurchaseButton() {
        click(purchaseButton);
    }

    public boolean isOrderSuccessful() {
        return isDisplayed(successMessage);
    }

    public void clickSweetAlertOk() {
        click(sweetAlertOkButton);
    }

    public void waitForSweetAlertToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(sweetAlertLocator));
    }

    public void closeOrderModalIfVisible() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement closeButton = shortWait.until(ExpectedConditions.elementToBeClickable(orderModalCloseButton));
            closeButton.click();

            //Wait modal
            wait.until(ExpectedConditions.invisibilityOfElementLocated(orderModalLocator));
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("'Place order' modal was not visible or closable.");
        }
    }

    public void handlePurchaseConfirmationFlow() {
        clickSweetAlertOk();
        waitForSweetAlertToDisappear();
        closeOrderModalIfVisible();
    }

}
