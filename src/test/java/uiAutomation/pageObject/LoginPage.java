package uiAutomation.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    //Login Page to Save Xpath Method
    private final By userClickLogin = By.xpath("//a[@id='login2']");
    private final By userNameLogin = By.xpath("//input[@id='loginusername']");
    private final By userPasswordLogin = By.xpath("//input[@id='loginpassword']");
    private final By loginButton = By.xpath("//button[@onclick='logIn()']");
    private final By nameOfUser = By.xpath("//a[@id='nameofuser']");

    public LoginPage(WebDriver driver){
        super(driver);
    }
    public void userClickLogin(){
        click(userClickLogin);
    }
    public void inputUsernameLogin(String usernameLogin){
        type(userNameLogin, usernameLogin);
    }
    public void inputPasswordLogin(String passwordLogin){
        type(userPasswordLogin, passwordLogin);
    }
    public void inputWrongPasswordLogin(String wrongPasswordLogin){
        type(userPasswordLogin, wrongPasswordLogin);
    }
    public void clickLoginButton(){
        click(loginButton);
    }
    public String getNameOfUser(){
        return getText(nameOfUser);
    }
}
