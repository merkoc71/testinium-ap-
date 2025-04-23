package com.testinium.trello.api.client;

import com.testinium.trello.api.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class BaseClient {
    protected final ConfigManager configManager;
    protected final RequestSpecification requestSpec;

    public BaseClient() {
        configManager = ConfigManager.getInstance();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(configManager.getTrelloApiBaseUrl())
                .addQueryParam("key", configManager.getTrelloApiKey())
                .addQueryParam("token", configManager.getTrelloApiToken())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);

        requestSpec = RestAssured.given().spec(requestSpecBuilder.build());
        
        log.info("Base client initialized with base URL: {}", configManager.getTrelloApiBaseUrl());
    }

    protected Response get(String endpoint) {
        log.info("Making GET request to: {}", endpoint);
        Response response = requestSpec.get(endpoint);
        logResponse(response);
        return response;
    }

    protected Response get(String endpoint, Map<String, Object> queryParams) {
        log.info("Making GET request to: {} with params: {}", endpoint, queryParams);
        Response response = requestSpec.queryParams(queryParams).get(endpoint);
        logResponse(response);
        return response;
    }

    protected Response post(String endpoint, Object requestBody) {
        log.info("Making POST request to: {} with body: {}", endpoint, requestBody);
        Response response = requestSpec.body(requestBody).post(endpoint);
        logResponse(response);
        return response;
    }

    protected Response put(String endpoint, Object requestBody) {
        log.info("Making PUT request to: {} with body: {}", endpoint, requestBody);
        Response response = requestSpec.body(requestBody).put(endpoint);
        logResponse(response);
        return response;
    }

    protected Response delete(String endpoint) {
        log.info("Making DELETE request to: {}", endpoint);
        Response response = requestSpec.delete(endpoint);
        logResponse(response);
        return response;
    }

    private void logResponse(Response response) {
        log.debug("Response Status Code: {}", response.getStatusCode());
        log.debug("Response Body: {}", response.getBody().asString());
    }
} 