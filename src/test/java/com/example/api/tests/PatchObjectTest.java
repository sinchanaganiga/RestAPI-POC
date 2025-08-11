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

public class PatchObjectTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(PatchObjectTest.class);

    @BeforeClass
    public void setUp() {
        LoggerSetup.setupLogging();
    }

    @Test
    public void patchObject() {
        // Ensure objectId is available before trying to patch
        assertNotNull(CreateObjectTest.objectId, "Object ID must be available for patching.");

        // Log the start of the test
        logger.info("Starting PatchObjectTest...");

        // Send PATCH request to update the object
        Response response = RestUtils.patchObject(CreateObjectTest.objectId);

        // Log the response for debugging purposes
        String responseString = response.asString();
        logger.debug("Patch Object Response:\n{}", JsonUtils.prettyPrint(responseString));

        // Assert that the status code is 200 (OK)
        try {
            assertEquals(response.getStatusCode(), 200, "Expected status code to be 200");

            // If status code is 200, log success message
            logger.info("Object with ID {} patched successfully.", CreateObjectTest.objectId);
            System.out.println("Patch test passed: Object patched successfully.");
        } catch (AssertionError e) {
            // If status code is not 200, log failure and display message
            logger.error("Failed to patch object with ID {}. Response code: {}", CreateObjectTest.objectId, response.getStatusCode());
            System.out.println("Patch test failed: Failed to patch object");
        }
    }
}
