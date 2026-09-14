package com.assessment.api.tests;

import com.assessment.api.config.ApiConfig;
import com.assessment.api.services.BooksService;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BooksTest {
    private BooksService booksService;

    @BeforeClass
    public void setUp() {
        booksService = new BooksService(ApiConfig.getBaseUrl());
    }

    @Test
    public void createBookReturnsSubmittedBook() {
        String bookJson;
        try (InputStream input = BooksTest.class.getClassLoader()
                .getResourceAsStream("testdata/create-book.json")) {
            if (input == null) {
                throw new IllegalStateException("testdata/create-book.json was not found on the test classpath");
            }
            bookJson = new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load testdata/create-book.json", exception);
        }
        JsonPath submitted = JsonPath.from(bookJson);

        Response response = booksService.createBook(bookJson);
        response.then().statusCode(200).contentType(ContentType.JSON);
        JsonPath returned = response.jsonPath();

        assertEquals(returned.getInt("id"), submitted.getInt("id"), "Book ID");
        assertEquals(returned.getString("title"), submitted.getString("title"), "Book title");
        assertEquals(returned.getString("description"), submitted.getString("description"), "Description");
        assertEquals(returned.getInt("pageCount"), submitted.getInt("pageCount"), "Page count");
        assertEquals(returned.getString("excerpt"), submitted.getString("excerpt"), "Excerpt");
        assertNotNull(returned.getString("publishDate"), "Publication timestamp must exist");
        assertEquals(OffsetDateTime.parse(returned.getString("publishDate")).toInstant(),
                OffsetDateTime.parse(submitted.getString("publishDate")).toInstant(),
                "Publication timestamp");
    }

    @Test
    public void getExistingBookReturnsBook() {
        Response response = booksService.getBookById(1);
        response.then().statusCode(200).contentType(ContentType.JSON);
        JsonPath book = response.jsonPath();

        assertEquals(book.getInt("id"), 1, "Book ID");
        String title = book.getString("title");
        assertNotNull(title, "Title must exist");
        assertFalse(title.isBlank(), "Title must not be blank");
        assertNotNull(book.getString("description"), "Description must exist");
        assertTrue(book.getInt("pageCount") > 0, "Page count must be positive");
        assertNotNull(book.getString("excerpt"), "Excerpt must exist");
        String publishDate = book.getString("publishDate");
        assertNotNull(publishDate, "Publication timestamp must exist");
        OffsetDateTime.parse(publishDate);
    }

    @Test
    public void getMissingBookReturnsNotFound() {
        Response response = booksService.getBookById(999999);
        response.then().statusCode(404).contentType("application/problem+json");
        JsonPath error = response.jsonPath();

        assertEquals(error.getInt("status"), 404, "Error status");
        assertEquals(error.getString("title"), "Not Found", "Error title");
    }
}
