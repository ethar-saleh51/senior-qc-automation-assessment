# UI Automation Assessment

## Overview

This project automates UI test scenarios for [The Internet](https://the-internet.herokuapp.com/).

## Technologies

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model

## Automated Tests

1. **File Upload** — Upload an image and verify successful upload.
2. **Dynamic Loading** — Open Example 2, click Start, wait for loading, and verify "Hello World!".

## Project Structure

```text
pom.xml
src/
├── main/java/com/seniorqcautomation/
│   ├── config/ConfigReader.java
│   ├── driver/DriverFactory.java
│   └── pages/
│       ├── BasePage.java
│       ├── HomePage.java
│       ├── FileUploadPage.java
│       ├── DynamicLoadingPage.java
│       └── DynamicLoadingExamplePage.java
└── test/
    ├── java/com/seniorqcautomation/
    │   ├── base/BaseTest.java
    │   └── tests/ui/
    │       ├── FileUploadTest.java
    │       └── DynamicLoadingTest.java
    └── resources/
        ├── config.properties
        ├── testng.xml
        └── testdata/images/upload-sample.png
```

## Prerequisites

- Java 21
- Maven
- Google Chrome

## Run Tests

From the project root:

```shell
mvn clean test
```

## Notes

- Selenium Manager handles ChromeDriver automatically.
- Explicit waits are used for synchronization.
- Test data and configuration are stored under test resources.
