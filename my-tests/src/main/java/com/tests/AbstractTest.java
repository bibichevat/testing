package com.tests;

import listener.ScreenListener;
import com.pages.main.MainPageObject;
import com.webdriver.BaseSelenium;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners({ScreenListener.class})
public class AbstractTest {
    private static final String NICKNAME = "tanyatest";
    private static final String PASSWORD = "testforlesson384";

    /**
     * Initialization of the WebDriver
     */
    //@BeforeTest
    @BeforeSuite
    public static void initSelenium() throws Exception {
        BaseSelenium.init();
    }

    protected static void loggingIn() {
        MainPageObject mainPage = new MainPageObject();
        mainPage.open();
        mainPage.signingIn(NICKNAME, PASSWORD);
        mainPage.isLogin(NICKNAME);
        //mainPage.isOpen();
    }

    @AfterSuite
    public static void closeSelenium() {
        BaseSelenium.close();
    }

}
