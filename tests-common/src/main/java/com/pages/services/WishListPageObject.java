package com.pages.services;

import com.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class WishListPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    //div[@class='s-width']/ul/li[1]/a

    @FindBy(xpath = "//a[@class='info-nick']")
    private WebElement toMenuButton;
    @FindBy(xpath = "//div[@class='touchcarousel-item ctg-slider__wrap ']//a[@href='/SONY-HDR-AS300.htm']")
    private WebElement currentCameraInWishList;
    @FindBy(xpath = "//div[@class='user-history-div'][1]/a[@href='/SONY-HDR-AS300.htm']")
    private WebElement currentCameraInHistory;
    @FindBy(xpath = "//div[@class='touchcarousel-item ctg-slider__wrap ']/div[@class='whishlist-action--remove']")
    private WebElement removeFromWishlistButton;
    @FindBy(xpath = "//div[@class='touchcarousel-item ctg-slider__wrap ']")
    private WebElement thingWishlist;
    @FindBy(xpath = ".//a[@class='wu-whishlist-slider--name']")
    private WebElement wishlistLbl;

    //</editor-folder>

    private final String PAGE_NAME = "'Закладки'";

    @Override
    protected String getPAGE_URL() {
        return "ek-wu.php?idwu_=gp8reijqfvg&view_=user";
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
        isOpen("Закладки", wishlistLbl);
    }

    public boolean isExistsInWishListWithoutAssert() {
        return isExists(currentCameraInWishList, "camera sony in wishlist");
    }
    public boolean isExistsInHistoryWithoutAssert() {
        return isExists(currentCameraInHistory, "camera sony in history");
    }

    public void goToWishList() {
        click(toMenuButton, "Кнопка 'Меню пользователя'");
    }

    public void deleteThing(){
        delete(removeFromWishlistButton, "из закладок");
    }
}
