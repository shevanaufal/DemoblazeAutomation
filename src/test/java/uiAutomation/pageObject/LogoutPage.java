package uiAutomation.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {
    //Logout Page to Save Xpath Object

    private final By userClickLogout = By.xpath("//a[@onclick='logOut()']");
    private final By validateLogout = By.xpath("//a[@id='login2']");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogOut() {
        click(userClickLogout);
    }
    public String validateLogOut() {
        return getText(validateLogout);
    }
}
