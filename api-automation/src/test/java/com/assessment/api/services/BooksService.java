package com.assessment.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class BooksService {
    private static final String BOOKS_ENDPOINT = "/api/v1/Books";
    private final String baseUrl;

    public BooksService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response createBook(String bookJson) {
        return request()
                .contentType(ContentType.JSON)
                .body(bookJson)
                .post(BOOKS_ENDPOINT);
    }

    public Response getBookById(int id) {
        return request()
                .pathParam("id", id)
                .get(BOOKS_ENDPOINT + "/{id}");
    }

    private RequestSpecification request() {
        return given().baseUri(baseUrl).accept(ContentType.JSON);
    }
}
