package com.pages.main;

import com.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CatalogPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //div[@class='s-width']/ul/li[1]/a

    @FindBy(xpath = ".//div[@class='s-width']/ul/li[1]/a")
    private WebElement gadgets;
    @FindBy(xpath = ".//a[@class='mainmenu-subitem mainmenu-icon695']")
    private WebElement cameras;
    @FindBy(xpath = ".//*[@id='li_br156']/label")
    private WebElement sony;
    @FindBy(xpath = ".//*[@id='preset18920']/li[8]/label")
    private WebElement nfc;
    @FindBy(xpath = ".//input[@id='match_submit']")
    private WebElement showButton;
    @FindBy(xpath = ".//a[@id='product_918494']/*")
    private WebElement camera;
    @FindBy(xpath = ".//*[@class='addto-wishlist heart-empty heart_918494']")
    private WebElement toFavoriteButton;

    private final String PAGE_NAME = "'Поиск по каталогу'";

    @Override
    protected String getPAGE_URL() {
        return "k695.htm";
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
        isOpen("Sony HDR-AS300", camera);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void beginSearch() {
        click(gadgets, "кнопка 'Гаджеты'");
        click(cameras, "кнопка 'action-камеры'");
        //scroll(sony, "Кнопка 'Sony");
        click(sony, "Кнопка 'Sony");
        scroll(nfc, "Кнопка 'nfc");
        click(nfc, "Кнопка 'nfc");
        scroll(showButton, "Кнопка 'Показать");
        click(showButton, "Кнопка 'Показать");
        click(camera, "Перейти к камере");
    }

    public void addToFavorite() {
        click(toFavoriteButton, "Кнопка 'В избранное");
    }
}
