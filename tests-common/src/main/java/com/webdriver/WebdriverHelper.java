package com.webdriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class WebdriverHelper {

    private static final long STANDART_WAIT = 20;
    private static final int ACCEPTABLE_PAUSE = 15;
    private static final String siteUrl = "https://www.e-katalog.ru/";

    private static final String JS_AJAX_PROGRESS =
            "var userWindow = window;" +
                    "var docReady = window.document.readyState == 'complete';" +
                    "var isJqueryComplete = typeof(userWindow.jQuery) != 'function' || userWindow.jQuery.active == 0;" +
                    "var isPrototypeComplete = typeof(userWindow.Ajax) != 'function' || userWindow.Ajax.activeRequestCount == 0;" +
                    "var isDojoComplete = typeof(userWindow.dojo) != 'function' || userWindow.dojo.io.XMLHTTPTransport.inFlight.length == 0;" +
                    "var result = docReady && isJqueryComplete && isPrototypeComplete && isDojoComplete;" +
                    "return result;";
    private static final ExpectedCondition<Object> isAJAXCompleted = webDriver -> {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        assert js != null;
        return Boolean.parseBoolean(js.executeScript(JS_AJAX_PROGRESS).toString());
    };
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void open(String PAGE_URL) {
        BaseSelenium.getSeleniumDriver().get(siteUrl + PAGE_URL);
    }

    public boolean isExists(WebElement element) {
        BaseSelenium.getSeleniumDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            element.isDisplayed();
        } catch (NoSuchElementException e) {
            log.debug("Element '" + element + "' actually doesn't exists");
            return false;
        } catch (NullPointerException e) {
            return false;
        } finally {
            BaseSelenium.getSeleniumDriver().manage().timeouts().implicitlyWait(STANDART_WAIT, TimeUnit.SECONDS);
        }
        return true;
    }

    public void isOpen(String PAGE_URL, String expectedText, WebElement checkingElement) {
        Assert.assertEquals(BaseSelenium.getSeleniumDriver().getCurrentUrl(), siteUrl + PAGE_URL);
        log.warn(BaseSelenium.getSeleniumDriver().getCurrentUrl());
        waitForPageUpdated();
        waitForElementToBeClickable(checkingElement);
        Assert.assertTrue(isExists(checkingElement));
        Assert.assertEquals(checkingElement.getText(), expectedText);
    }

    public void isOpen(String expectedText, WebElement checkingElement) {
        log.warn(BaseSelenium.getSeleniumDriver().getCurrentUrl());
        waitForPageUpdated();
        waitForElementToBeClickable(checkingElement);
        Assert.assertTrue(isExists(checkingElement));
        Assert.assertEquals(checkingElement.getText(), expectedText);
    }

    public void click(WebElement webElement) {
        try {
            waitForPageUpdated();
            waitForElementToBeClickable(webElement);
            webElement.click();
        } catch (Exception e) {
            log.error("There is some problem with clicking");
        } finally {
            log.debug("Clicked");
            waitForPageUpdated();
        }
    }

    public void sendKeys(WebElement webElement, String inputText) {
        try {
            webElement.clear();
            webElement.sendKeys(inputText);
        } catch (Exception e) {
            System.out.println("Exception in 'Send Keys'");
        }
    }

    public void delete(WebElement webElement) {
        try {
            waitForPageUpdated();
            //waitForElementToBeClickable(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) BaseSelenium.getSeleniumDriver();
            executor.executeScript("arguments[0].click();", webElement);
        } catch (Exception e) {
            System.out.println("Exception in 'delete'");
        }
    }

    public void executeJavaScript(String script) {
        JavascriptExecutor js;
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        } else {
            throw new IllegalStateException("This driver does not support JavaScript!");
        }

        js.executeScript(script);
    }

    private void waitForElementToBeClickable(WebElement element) {
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        try {
            new WebDriverWait(driver, ACCEPTABLE_PAUSE).until(ExpectedConditions.elementToBeClickable(element));

        } finally {
            log.debug("Waiting For Element To Be Clickable");
        }
    }

    private void waitForPageUpdated() {
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, ACCEPTABLE_PAUSE, 20);
            wait.until(isAJAXCompleted);
        } finally {
            log.debug("Waiting for page update");
        }
    }
}

