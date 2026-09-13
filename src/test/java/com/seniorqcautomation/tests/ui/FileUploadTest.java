package com.seniorqcautomation.tests.ui;

import com.seniorqcautomation.base.BaseTest;
import com.seniorqcautomation.pages.FileUploadPage;
import com.seniorqcautomation.pages.HomePage;
import java.nio.file.Path;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {
    @Test
    public void shouldUploadImageSuccessfully() throws InterruptedException {
        Path imagePath = resolveTestResource("testdata/images/upload-sample.png");
        HomePage homePage = new HomePage(driver, config.getExplicitWaitTimeout());

        homePage.open(config.getBaseUrl());
        Thread.sleep(3_000); // TEMPORARY: visually inspect the home page.
        FileUploadPage fileUploadPage = homePage.clickFileUpload();
        Thread.sleep(3_000); // TEMPORARY: visually inspect the upload page.
        fileUploadPage.selectImage(imagePath);
        Thread.sleep(3_000); // TEMPORARY: visually inspect the selected filename.
        fileUploadPage.submitUpload();
        Thread.sleep(3_000); // TEMPORARY: visually inspect the submitted result.

        Assert.assertEquals(fileUploadPage.getResultHeading(), "File Uploaded!",
                "The upload success heading should be displayed.");
        Assert.assertEquals(fileUploadPage.getUploadedFileName(), imagePath.getFileName().toString(),
                "The uploaded filename should match the selected image.");
        Thread.sleep(10_000); // TEMPORARY: keep the successful result open before teardown.
    }
}
