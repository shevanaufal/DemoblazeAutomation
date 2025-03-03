package uiAutomation.pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddtoCartPage extends BasePage {
    //Add to Cart to Save Object Xpath
    public AddtoCartPage(WebDriver driver) {
        super(driver);
    }

    public void openProductDetailsPage(String productName) {
        By productDetailsPage = By.xpath("//a[contains(text(), '" + productName + "')]");
        click(productDetailsPage);
    }

    public void clickAddToCart(String productID) {
        By addtoCartProduct = By.xpath("//a[contains(@class,'btn-success') and contains(@onclick,'addToCart(" + productID + ")')]");
        click(addtoCartProduct);

    }
}
