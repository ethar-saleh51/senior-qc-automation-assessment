package com.seniorqcautomation.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicLoadingExamplePage extends BasePage {
    private final By startButton = By.cssSelector("#start button");
    private final By resultText = By.cssSelector("#finish h4");

    public DynamicLoadingExamplePage(WebDriver driver, Duration waitTimeout) {
        super(driver, waitTimeout);
    }

    public void clickStart() {
        wait.until(ExpectedConditions.elementToBeClickable(startButton)).click();
    }

    public String getResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultText)).getText();
    }
}
