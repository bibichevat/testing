package com.pages.services;

import com.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FilterPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    //*[@id="mui_user_login_row"]/span/a

    @FindBy(xpath = "//div[@class='s-width']/ul/li[2]/a")
    private WebElement computers;
    @FindBy(xpath = "//div[@class='s-width']/ul/li[2]/div/div/a[2]")
    private WebElement tablets;
    @FindBy(xpath = "//input[@id='minPrice_']")
    private WebElement minPriceInput;
    @FindBy(xpath = "//input[@id='match_submit']")
    private WebElement showButton;

    //</editor-folder>

    private final String PAGE_NAME = "'Планшеты'";

    @Override
    protected String getPAGE_URL() {
        return "k30.htm";
    }
    @Override
    protected String getPAGE_NAME() {
        return PAGE_NAME;
    }

    /**
     * Check that Main page is opened.
     */
    public void isOpen() {
        //log.info("Is page opened " + getPAGE_NAME());
        //isOpen("tanyatest", nick);
    }

    public void beginFilter(String price) {
        click(computers, "кнопка 'Компьютеры'");
        click(tablets, "кнопка 'Планшеты'");
        sendKeys(minPriceInput, price, "'Цена'");
        scroll(showButton, "кнопка 'Подобрать'");
        click(showButton, "кнопка 'Подобрать'");
    }


}


