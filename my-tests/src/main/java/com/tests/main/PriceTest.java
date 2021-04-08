package com.tests.main;

import com.annotations.TestCaseID;
import com.pages.main.MainPageObject;
import com.pages.services.FilterPageObject;
import com.pages.services.PricePageObject;
import com.tests.AbstractTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PriceTest extends AbstractTest {
    @BeforeClass
    public static void login() {
        loggingIn();
    }

    @Test(description = "Поиск по цене", priority = 3)
    @Parameters({"price"})
    @TestCaseID("104")
    public void testPrice(String price) throws InterruptedException {
        FilterPageObject filterPage = new FilterPageObject();
        PricePageObject pricePage = new PricePageObject();
        filterPage.beginFilter(price);
        Thread.sleep(5000);
        pricePage.beginCheckPrice(price);
    }

    @AfterClass
    public static void logout() throws InterruptedException {
        MainPageObject mainPage = new MainPageObject();
        if (mainPage.isLoginWithoutAssert()) {
            mainPage.logOut();
        }
    }
}