package com.webdriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class BaseSelenium {
    private static WebDriver driver;

    public static WebDriver getSeleniumDriver() {
        return driver;
    }

    public static void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void close() {
        try {
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            driver = null;
        }
    }
}
