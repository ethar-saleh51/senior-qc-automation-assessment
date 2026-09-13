package com.seniorqcautomation.base;

import com.seniorqcautomation.config.ConfigReader;
import com.seniorqcautomation.driver.DriverFactory;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected ConfigReader config;
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        config = new ConfigReader();
        driver = new DriverFactory().createDriver(config.getBrowser());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }

    protected Path resolveTestResource(String resourceName) {
        URL resource = getClass().getClassLoader().getResource(resourceName);
        if (resource == null) {
            throw new IllegalArgumentException("Test resource was not found: " + resourceName);
        }
        if (!"file".equals(resource.getProtocol())) {
            throw new IllegalArgumentException("Test resource must be a local file: " + resourceName);
        }
        try {
            Path path = Path.of(resource.toURI()).toAbsolutePath().normalize();
            if (!Files.isRegularFile(path) || !Files.isReadable(path)) {
                throw new IllegalArgumentException("Test resource is not a readable file: " + resourceName);
            }
            return path;
        } catch (URISyntaxException exception) {
            throw new IllegalArgumentException("Invalid test resource URI: " + resourceName, exception);
        }
    }
}
