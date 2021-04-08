package com.tests.main;

import com.annotations.TestCaseID;
import com.pages.main.MainPageObject;
import com.tests.AbstractTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends AbstractTest {
    @BeforeClass
    public static void login() {
        MainPageObject mainPage = new MainPageObject();
        loggingIn();
    }

    @BeforeMethod
    public void preparation() throws InterruptedException {
        logout();
    }

    @Test(description = "Авторизация", priority=1)
    @TestCaseID("102")
    public void checkLogin() {
        loggingIn();
    }

    @AfterClass
    public static void logout() throws InterruptedException {
        MainPageObject mainPage = new MainPageObject();
        if (mainPage.isLoginWithoutAssert()) {
            mainPage.logOut();
        }
        Thread.sleep(10000);
        mainPage.open();
        mainPage.IsLoggedOut();
    }
}