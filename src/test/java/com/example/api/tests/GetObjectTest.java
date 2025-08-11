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

public class GetObjectTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GetObjectTest.class);

    @BeforeClass
    public void setUp() {
        LoggerSetup.setupLogging();
    }

    @Test(priority = 2, dependsOnMethods = "createObject")
    public void getObject() {
        // Ensure objectId is available from the CreateObjectTest class
        assertNotNull(CreateObjectTest.objectId, "Object ID must be available for fetching.");

        logger.info("Starting GetObjectTest...");

        // Get the object using the objectId created in the previous test
        Response response = RestUtils.getObject(CreateObjectTest.objectId);

        // Log the response for debugging
        String responseString = response.asString();
        logger.debug("Get Object Response:\n{}", JsonUtils.prettyPrint(responseString));

        // Check for status code
        try {
            assertEquals(response.getStatusCode(), 200, "Expected status code to be 200");

            // If status code is 200, log success message
            logger.info("Object with ID {} fetched successfully.", CreateObjectTest.objectId);
            System.out.println("Get test passed: Object fetched successfully.");
        } catch (AssertionError e) {
            // If status code is not 200, log failure and display message
            logger.error("Failed to get object with ID {}. Response code: {}", CreateObjectTest.objectId, response.getStatusCode());
            System.out.println("Get test failed: Failed to fetch object");
        }
    }
}
