package com.pages;

import com.webdriver.BaseSelenium;
import com.webdriver.WebdriverHelper;
import org.openqa.selenium.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPageObject {
    protected final WebdriverHelper webdriverHelper = new WebdriverHelper();
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//*[@class='info-nick']")
    private WebElement nick;
    @FindBy(xpath = ".//*[@class='help2']")
    private WebElement logoutButton;

    protected AbstractPageObject() {
        PageFactory.initElements(BaseSelenium.getSeleniumDriver(), this);
    }

    protected abstract String getPAGE_URL();
    protected abstract String getPAGE_NAME();

    public abstract void isOpen();

    public void open() {
        log.info("Opening the " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    protected void isOpen(String expectedText, WebElement checkingElement) {
        log.info("Checking that webelement even exists and equals to '" + expectedText + "'");
        webdriverHelper.isOpen(getPAGE_URL(), expectedText, checkingElement);
    }

    public void isLogin(String login) {
        isOpen(login, nick);
    }

    public boolean isLoginWithoutAssert() {
        return isExists(nick, "tanyatest");
    }

    /**
     * Logging out
     */
    public void logOut() {
        log.info("Logging out from " + getPAGE_NAME());
        click(nick, "кнопка 'tanyatest'");
        click(logoutButton, "кнопка 'Выйти'");
    }

    protected boolean isExists(WebElement element, String elemName) {
        log.info("Checking that " + elemName + " exists on the " + getPAGE_NAME());
        return webdriverHelper.isExists(element);
    }

    protected void click(WebElement webElement, String webElemNameForLog) {
        webdriverHelper.isExists(webElement);
        log.info("Нажимается " + webElemNameForLog);
        webdriverHelper.click(webElement);
    }

    protected void scroll(WebElement webElement, String webElemNameForLog) {
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        JavascriptExecutor scroller = (JavascriptExecutor) driver;
        scroller.executeScript("arguments[0].scrollIntoView(true);", webElement);
        log.info("Scroll to " + webElemNameForLog);
    }

    protected void sendKeys(WebElement webElement, String inputText, String inputName) {
        log.info("Sending keys into the " + inputName + " on the " + getPAGE_NAME() + ".\tKeys: " + inputText);
        webdriverHelper.sendKeys(webElement, inputText);
    }

    protected void delete(WebElement webElement, String webElemNameForLog) {
        log.info("delete " + webElemNameForLog);
        webdriverHelper.delete(webElement);
    }

    public int findElement(By xpath) {
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        try {
            return Integer.parseInt(driver.findElement(xpath).getText());
        } catch (Exception ex) {
            return 1;
        }
    }
}