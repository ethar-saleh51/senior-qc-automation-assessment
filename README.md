# Senior Software QC Automation Assessment

## Overview

This repository contains UI and API automation implemented as two independent Maven projects. The solution focuses on clean, maintainable, reusable test automation without unnecessary framework complexity.

## Technologies

- Java 21
- TestNG
- Maven
- Git

Project-specific automation libraries:

- Selenium WebDriver - UI automation
- REST Assured - API automation

## Project Structure

```text
senior-qc-automation/
├── ui-automation/
│   ├── pom.xml
│   └── src/
│       ├── main/java/.../pages/
│       └── test/
├── api-automation/
│   ├── pom.xml
│   └── src/test/
│       ├── java/.../config/
│       ├── java/.../services/
│       ├── java/.../tests/
│       └── resources/
└── README.md
```

Each automation project has its own `pom.xml` and can be opened and executed independently.

## UI Automation

Target: [The Internet](https://the-internet.herokuapp.com/)

Automated scenarios:

1. **File Upload**
   - Navigate from the home page to File Upload.
   - Upload the test image.
   - Submit the upload.
   - Verify successful upload and the uploaded filename.
2. **Dynamic Loading - Example 2**
   - Navigate from the home page to Dynamic Loading.
   - Open Example 2.
   - Start loading.
   - Wait using Selenium explicit synchronization.
   - Verify the result is exactly "Hello World!".

Page Object Model is used. Browser, base URL, and wait configuration are externalized. The test image is stored under test resources, and Selenium Manager handles ChromeDriver automatically.

## API Automation

Target: [FakeRESTApi](https://fakerestapi.azurewebsites.net/)

Automated scenarios:

1. **POST /api/v1/Books - Happy path**
   - Create a book and validate the returned response against submitted test data.
2. **GET /api/v1/Books/{id} - Happy path**
   - Retrieve an existing book and validate its response.
3. **GET /api/v1/Books/{id} - Negative path**
   - Request a missing book and validate the 404 response.

`BooksService` encapsulates API request execution, while assertions remain in the test layer. The API base URL is externalized in `config.properties`, and POST book data is externalized in `testdata/create-book.json`.

Tests are independent. POST validation does not rely on persistence because FakeRESTApi may simulate create operations.

## Prerequisites

- JDK 21
- Maven
- Google Chrome for UI tests

Java and Maven should be available from the command line.

## Running the Tests

### Run UI Tests

From the repository root:

```shell
cd ui-automation
mvn clean test
```

Expected: 2 tests executed.

### Run API Tests

From the repository root:

```shell
cd api-automation
mvn clean test
```

Expected: 3 tests executed.

Each project can also be opened and executed independently because each has its own `pom.xml`.

## Expected Test Execution

- UI Automation: 2 tests
- API Automation: 3 tests
