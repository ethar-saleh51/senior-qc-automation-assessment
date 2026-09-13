package com.seniorqcautomation.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Duration waitTimeout;

    protected BasePage(WebDriver driver, Duration waitTimeout) {
        this.driver = driver;
        this.waitTimeout = waitTimeout;
        this.wait = new WebDriverWait(driver, waitTimeout);
    }
}
