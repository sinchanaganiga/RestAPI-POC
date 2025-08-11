package com.example.api.tests;

import com.example.api.base.BaseTest;
import com.example.api.utils.JsonUtils;
import com.example.api.utils.LoggerSetup;
import com.example.api.utils.RestUtils;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateObjectTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateObjectTest.class);
    public static String objectId;

    @BeforeClass
    public void setUp() {
        LoggerSetup.setupLogging();  // Set up logging
    }

    @Test(priority = 1)
    public void createObject() {

        logger.info("Starting CreateObjectTest...");

        // Create the object using the RestUtils
        Response response = RestUtils.createObject();

        // Log the response body in a pretty format
        String responseString = response.asString();
        logger.debug("Create Object Response:\n{}", JsonUtils.prettyPrint(responseString));

        // Asserting that the status code is 200
        assertEquals(response.getStatusCode(), 200, "Expected status code to be 200");

        // Get the object ID from the response and store it for further use
        objectId = response.jsonPath().getString("id");

        // Validate the objectId to ensure that it's not null or empty
        assertNotNull(objectId, "Object ID should not be null");

        // Log the object ID
        if (objectId != null && !objectId.isEmpty()) {
            logger.info("Successfully created object with ID: {}", objectId);
            System.out.println("Create test passed: Object created successfully");
        } else {
            logger.error("Failed to create object. Object ID is null.");
            System.out.println("Create test failed: Object creation failed.");
        }
    }
}
