package uiAutomation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Environment {
    private static Environment instance;
    private WebDriver driver;

    private Environment(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=" + System.getProperty("user.home") + "/selenium_profile"); // Unique profile directory
        options.addArguments("--disable-blink-features=AutomationControlled"); // Avoid detection
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        String baseUrl = "https://www.demoblaze.com/";
        driver.get(baseUrl);
    }
    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
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
