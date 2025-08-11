package com.example.api.utils;

import com.example.api.config.Constants;
import com.example.api.pojo.ObjectPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

import static io.restassured.RestAssured.*;

public class RestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper for converting POJOs to JSON

    //Allure attachment helper
    private static void attachToAllure(String name, String json) {
        Allure.addAttachment(name, "application/json", new ByteArrayInputStream(json.getBytes()), ".json");
    }

    // POST - Create Object
    public static Response createObject() {
        ObjectPayload payload = FakeDataFactory.generateCreateObjectPayload(); // Generate random payload
        String payloadAsJson = convertPojoToJson(payload); // Convert POJO to JSON string

        logger.info("Creating object with payload: {}", payloadAsJson);
        attachToAllure("Request Payload - Create", payloadAsJson);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payloadAsJson)
                .when()
                .post(Constants.CREATE_OBJECT);

        attachToAllure("Response Body - Create", response.asString());
        return response;
    }

    // GET - Fetch Object
    public static Response getObject(String id) {
        logger.info("Fetching object with ID: {}", id);

        Response response = given()
                .when()
                .get(Constants.GET_OBJECT.replace("{id}", id));

        attachToAllure("Response Body - Get", response.asString());
        return response;
    }

    // PUT - Update Object
    public static Response updateObject(String id) {
        ObjectPayload payload = FakeDataFactory.generateUpdateObjectPayload(); // Generate random payload
        String payloadAsJson = convertPojoToJson(payload); // Convert POJO to JSON string

        logger.info("Updating object with ID: {} and payload: {}", id, payloadAsJson);
        attachToAllure("Request Payload - Update", payloadAsJson);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payloadAsJson)
                .when()
                .put(Constants.UPDATE_OBJECT.replace("{id}", id));

        attachToAllure("Response Body - Update", response.asString());
        return response;
    }

    // PATCH - Partially Update Object
    public static Response patchObject(String id) {
        ObjectPayload payload = FakeDataFactory.generatePatchObjectPayload(); // Generate random payload
        String payloadAsJson = convertPojoToJson(payload); // Convert POJO to JSON string

        logger.info("Partially updating object with ID: {} and payload: {}", id, payloadAsJson);
        attachToAllure("Request Payload - Patch", payloadAsJson);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payloadAsJson)
                .when()
                .patch(Constants.PATCH_OBJECT.replace("{id}", id));

        attachToAllure("Response Body - Patch", response.asString());
        return response;
    }

    // DELETE - Delete Object
    public static Response deleteObject(String id) {
        logger.info("Deleting object with ID: {}", id);

        Response response = given()
                .when()
                .delete(Constants.DELETE_OBJECT.replace("{id}", id));

        attachToAllure("Response Body - Delete", response.asString());
        return response;
    }

    // Convert POJO to JSON string
    private static String convertPojoToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object); // Convert the POJO to JSON string
        } catch (Exception e) {
            logger.error("Error converting POJO to JSON: {}", e.getMessage());
            return "{}"; // Return empty JSON object if conversion fails
        }
    }
}
