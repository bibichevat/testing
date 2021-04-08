package com.pages.services;

import com.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class PricePageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    //*[@id="mui_user_login_row"]/span/a

    @FindBy(xpath = "//div[@class='ib page-num']//a[last()]")
    private WebElement lastPage;
    @FindBy(xpath = "//form[@id='list_form1']")
    private WebElement all;
    @FindBy(xpath = ".//a[@class = 'ib pager-next']")
    private WebElement nextPage;
    @FindBy(xpath = ".//div[@class='ib page-num']//a[last()]")
    private WebElement last;
    @FindBy(xpath = ".//*[@class='info-nick']")
    private WebElement nick;

    //</editor-folder>

    private final String PAGE_NAME = "'Планшеты'";

    @Override
    protected String getPAGE_URL() {
        return "ek-list.php?katalog_=30&pf_=1&minPrice_=1000&save_podbor_=1";
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

    public void beginCheckPrice(String price) {

        int priceInt = Integer.parseInt(price);
        int lastPage;
        scroll(last, "For show");
        lastPage = findElement(By.xpath(".//div[@class='ib page-num']//a[last()]"));
        scroll(nick, "nick");

        for (int i = 1; i <= lastPage; i++) {

            List<WebElement> listTablet = all.findElements(By.xpath(".//form[@id='list_form1']/div[@id]"));

            if (i == lastPage)
                listTablet.remove(listTablet.size() - 1);

            for (WebElement thing : listTablet) {
                WebElement current;
                current = thing.findElement(By.xpath(".//div[@class = 'model-price-range']//span[1]"));

                Assert.assertTrue(priceInt > Integer.parseInt(current.getText().replace(" ", ""))
                );
            }

            if (i < lastPage)
                scroll(nextPage, "next");
                nextPage.click();
        }
    }
}



