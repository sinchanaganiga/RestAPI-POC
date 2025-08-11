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

public class DeleteObjectTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DeleteObjectTest.class);

    @BeforeClass
    public void setUp() {
        LoggerSetup.setupLogging();
    }

    @Test
    public void deleteObject() {

        // Ensure objectId is available from the CreateObjectTest class
        assertNotNull(CreateObjectTest.objectId, "Object ID must be available for deletion.");

        logger.info("Starting DeleteObjectTest...");

        // Send the DELETE request using the objectId
        Response response = RestUtils.deleteObject(CreateObjectTest.objectId);

        // Log the response body for debugging purposes
        String responseString = response.asString();
        logger.debug("Delete Object Response:\n{}", JsonUtils.prettyPrint(responseString));

        // Assert that the status code is 200 (OK)
        try {
            assertEquals(response.getStatusCode(), 200, "Expected status code to be 200");

            // If status code is 200, log success message
            logger.info("Object with ID {} deleted successfully.", CreateObjectTest.objectId);
            System.out.println("Delete test passed: Object deleted successfully.");
        } catch (AssertionError e) {
            // If status code is not 200, log failure message
            logger.error("Failed to delete object with ID {}. Response code: {}", CreateObjectTest.objectId, response.getStatusCode());
            System.out.println("Delete test failed: Failed to delete object");
        }
    }
}
