package com.tests.main;

import com.annotations.TestCaseID;
import com.pages.main.MainPageObject;
import com.pages.main.CatalogPageObject;
import com.pages.services.WishListPageObject;
import com.tests.AbstractTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CatalogTest extends AbstractTest {
    @BeforeClass
    public static void login() {
        loggingIn();
    }

    @Test(description = "Поиск по каталогу, добавление в закладки", priority = 2)
    @TestCaseID("103")
    public void testCatalog() throws InterruptedException {
        MainPageObject mainPage = new MainPageObject();
        CatalogPageObject catalogPage = new CatalogPageObject();
        WishListPageObject wishListPage = new WishListPageObject();
        mainPage.open();
        mainPage.isOpen();
        Thread.sleep(2000);
        catalogPage.beginSearch();
        catalogPage.addToFavorite();
        wishListPage.goToWishList();
        Thread.sleep(1000);
        wishListPage.isOpen();
        wishListPage.isExistsInWishListWithoutAssert();
        wishListPage.isExistsInHistoryWithoutAssert();
    }

    @AfterClass
    public void testCleanUp() throws InterruptedException {
        WishListPageObject wishListPage = new WishListPageObject();
        wishListPage.deleteThing();
        MainPageObject mainPage = new MainPageObject();
        if (mainPage.isLoginWithoutAssert()) {
            mainPage.logOut();
        }
    }
}
