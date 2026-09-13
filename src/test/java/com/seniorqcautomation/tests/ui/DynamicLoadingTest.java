package com.seniorqcautomation.tests.ui;

import com.seniorqcautomation.base.BaseTest;
import com.seniorqcautomation.pages.DynamicLoadingExamplePage;
import com.seniorqcautomation.pages.DynamicLoadingPage;
import com.seniorqcautomation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTest extends BaseTest {
    @Test
    public void shouldDisplayHelloWorldAfterLoading() {
        HomePage homePage = new HomePage(driver, config.getExplicitWaitTimeout());
        homePage.open(config.getBaseUrl());
        DynamicLoadingPage dynamicLoadingPage = homePage.clickDynamicLoading();
        DynamicLoadingExamplePage examplePage = dynamicLoadingPage.clickExampleTwo();
        examplePage.clickStart();

        Assert.assertEquals(examplePage.getResultText(), "Hello World!",
                "The dynamically loaded result should display the expected greeting.");
    }
}
