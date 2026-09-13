package com.seniorqcautomation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public WebDriver createDriver(String browser) {
        if (!"chrome".equalsIgnoreCase(browser)) {
            throw new IllegalArgumentException("Unsupported browser: " + browser + ". Only Chrome is supported.");
        }
        return new ChromeDriver();
    }
}
