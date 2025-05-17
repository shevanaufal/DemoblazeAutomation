package uiAutomation.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {
    //ContactPage to Save Xpath Object

    private final By contactModal = By.xpath("//a[@data-target='#exampleModal']");
    private final By contactEmailField = By.xpath("//input[@id='recipient-email']");
    private final By contactNameField = By.xpath("//input[@id='recipient-name']");
    private final By contactMessageField = By.xpath("//textarea[@id='message-text']");
    private final By confirmSendMessage = By.xpath("//button[@onclick='send()']");

    public ContactPage(WebDriver driver) {
        super(driver);
    }
    public void openContactModal() {
        click(contactModal);
    }
    public void inputContactEmail(String contactEmail) {
        type(contactEmailField, contactEmail);
    }
    public void inputContactName(String contactName) {
        type(contactNameField, contactName);
    }
    public void inputContactMessage(String contactMessage) {
        type(contactMessageField, contactMessage);
    }
    public void clickSendMessage() {
        click(confirmSendMessage);
    }

}
