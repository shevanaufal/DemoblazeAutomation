package uiAutomation.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {

    private final By demoblazeHomepage = By.xpath("//a[@class='navbar-brand']");
    private final By userClickSignUp = By.xpath("//a[@id='signin2']");
    private final By userInputUsername = By.xpath("//input[@id='sign-username']");
    private final By userInputPassword = By.xpath("//input[@id='sign-password']");
    private final By userConfirmSignUp = By.xpath("//button[@onclick='register()']");

    public SignupPage(WebDriver driver){
        super(driver);
    }

    public String titleBrand(){
        return getText(demoblazeHomepage);
    }
    public void clickSignUpButton(){
        click(userClickSignUp);
    }
    public void inputUsername(String username){
        type(userInputUsername, username);
    }
    public void inputPassword(String password){
        type(userInputPassword, password);
    }
    public void confirmSignUp(){
        click(userConfirmSignUp);
    }

}
