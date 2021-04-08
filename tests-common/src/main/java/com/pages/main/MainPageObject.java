package com.pages.main;

import com.pages.AbstractPageObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MainPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    //*[@id="mui_user_login_row"]/span/a

    @FindBy(xpath = ".//*[@class='wu_entr']")
    private WebElement enterButton;
    @FindBy(xpath = ".//*[@class='signin-with signin-with-ek d-flex justify-content-center align-items-center']")
    private WebElement loginButton;
    @FindBy(xpath = ".//input[@name='l_']")
    private WebElement loginField;
    @FindBy(xpath = ".//input[@name='pw_']")
    private WebElement passwordField;
    @FindBy(xpath = ".//button[@class='ek-form-btn blue']")
    private WebElement signInButton;
    @FindBy(xpath = ".//*[@class='help2']")
    private WebElement logoutButton;
    @FindBy(xpath = ".//*[@class='ib blue brand-page-title']")
    private WebElement popularLbl;
    @FindBy(xpath = ".//*[@class='info-nick']")
    private WebElement nick;


    //</editor-folder>

    private final String PAGE_NAME = "'Главная страница'";

    @Override
    protected String getPAGE_URL() {
        return "";
    }
    @Override
    protected String getPAGE_NAME() {
        return PAGE_NAME;
    }

    /**
     * Check that Main page is opened.
     */
    public void isOpen() {
        log.info("Is page opened " + getPAGE_NAME());
        isOpen("tanyatest", nick);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void signingIn(String login, String password) {
        click(enterButton, "кнопка 'Войти'");
        click(loginButton, "текст 'e-mail'");
        sendKeys(loginField, login, "'Логин'");
        sendKeys(passwordField, password, "'Пароль'");
        click(signInButton, "кнопка 'Войти'");
    }

    public void IsLoggedOut() {
        Assert.assertFalse(isLoginWithoutAssert());
    }

}
