package com.seniorqcautomation.tests.ui;

import com.seniorqcautomation.base.BaseTest;
import com.seniorqcautomation.pages.DynamicLoadingExamplePage;
import com.seniorqcautomation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTest extends BaseTest {
    @Test
    public void shouldDisplayHelloWorldAfterLoading() {
        DynamicLoadingExamplePage examplePage = new HomePage(driver, config.getExplicitWaitTimeout())
                .open(config.getBaseUrl())
                .clickDynamicLoading()
                .clickExampleTwo()
                .clickStart();

        Assert.assertEquals(examplePage.getResultText(), "Hello World!",
                "The dynamically loaded result should display the expected greeting.");
    }
}
