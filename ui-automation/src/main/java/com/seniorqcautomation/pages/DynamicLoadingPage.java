package com.seniorqcautomation.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicLoadingPage extends BasePage {
    private final By exampleTwoLink = By.linkText("Example 2: Element rendered after the fact");

    public DynamicLoadingPage(WebDriver driver, Duration waitTimeout) {
        super(driver, waitTimeout);
    }

    public DynamicLoadingExamplePage clickExampleTwo() {
        wait.until(ExpectedConditions.elementToBeClickable(exampleTwoLink)).click();
        return new DynamicLoadingExamplePage(driver, waitTimeout);
    }
}
