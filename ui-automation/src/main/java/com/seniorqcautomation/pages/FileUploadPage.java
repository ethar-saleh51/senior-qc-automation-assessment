package com.seniorqcautomation.pages;

import java.nio.file.Path;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FileUploadPage extends BasePage {
    private final By fileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By resultHeading = By.cssSelector(".example h3");
    private final By uploadedFileName = By.id("uploaded-files");

    public FileUploadPage(WebDriver driver, Duration waitTimeout) {
        super(driver, waitTimeout);
    }

    public void selectImage(Path imagePath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInput))
                .sendKeys(imagePath.toAbsolutePath().normalize().toString());
    }

    public void submitUpload() {
        wait.until(ExpectedConditions.elementToBeClickable(uploadButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFileName));
    }

    public String getResultHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultHeading)).getText().trim();
    }

    public String getUploadedFileName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFileName)).getText().trim();
    }
}
