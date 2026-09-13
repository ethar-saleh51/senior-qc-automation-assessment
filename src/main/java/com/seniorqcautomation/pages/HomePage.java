package com.seniorqcautomation.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private final By fileUploadLink = By.linkText("File Upload");
    private final By dynamicLoadingLink = By.linkText("Dynamic Loading");

    public HomePage(WebDriver driver, Duration waitTimeout) {
        super(driver, waitTimeout);
    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    public FileUploadPage clickFileUpload() {
        wait.until(ExpectedConditions.elementToBeClickable(fileUploadLink)).click();
        return new FileUploadPage(driver, waitTimeout);
    }

    public DynamicLoadingPage clickDynamicLoading() {
        wait.until(ExpectedConditions.elementToBeClickable(dynamicLoadingLink)).click();
        return new DynamicLoadingPage(driver, waitTimeout);
    }
}
