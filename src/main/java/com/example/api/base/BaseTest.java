package com.example.api.base;

import com.example.api.config.Constants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Constants.BASE_URI;
    }
}
