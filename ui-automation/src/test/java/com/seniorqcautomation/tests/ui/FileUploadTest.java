package com.seniorqcautomation.tests.ui;

import com.seniorqcautomation.base.BaseTest;
import com.seniorqcautomation.pages.FileUploadPage;
import com.seniorqcautomation.pages.HomePage;
import java.nio.file.Path;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {
    @Test
    public void shouldUploadImageSuccessfully() {
        Path imagePath = resolveTestResource("testdata/images/upload-sample.png");
        HomePage homePage = new HomePage(driver, config.getExplicitWaitTimeout());

        homePage.open(config.getBaseUrl());
        FileUploadPage fileUploadPage = homePage.clickFileUpload();
        fileUploadPage.selectImage(imagePath);
        fileUploadPage.submitUpload();

        Assert.assertEquals(fileUploadPage.getResultHeading(), "File Uploaded!",
                "The upload success heading should be displayed.");
        Assert.assertEquals(fileUploadPage.getUploadedFileName(), imagePath.getFileName().toString(),
                "The uploaded filename should match the selected image.");
    }
}
