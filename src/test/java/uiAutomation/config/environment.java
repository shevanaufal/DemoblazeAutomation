package uiAutomation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class environment {
    private static environment instance;
    private WebDriver driver;

    private environment(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        String baseUrl = "https://www.demoblaze.com/";
        driver.get(baseUrl);
    }
    public static environment getInstance() {
        if (instance == null) {
            instance = new environment();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            instance = null;
        }
    }
}
