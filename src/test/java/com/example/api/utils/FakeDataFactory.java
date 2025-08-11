package com.example.api.utils;

import com.example.api.pojo.ObjectData;
import com.example.api.pojo.ObjectPayload;
import com.github.javafaker.Faker;

public class FakeDataFactory {

    private static final Faker faker = new Faker();

    // Generates a random payload for creating an object
    public static ObjectPayload generateCreateObjectPayload() {
        // Create a new ObjectData instance and populate it with random values
        ObjectData data = new ObjectData();
        data.setColor(faker.color().name());
        data.setCapacity(faker.number().numberBetween(64, 512) + " GB");

        // Create an ObjectPayload instance and set the name and the data
        ObjectPayload payload = new ObjectPayload();
        payload.setName(faker.commerce().productName());
        payload.setData(data);

        return payload;
    }

    // Generates a random payload for updating an object
    public static ObjectPayload generateUpdateObjectPayload() {
        // Create a new ObjectData instance and populate it with random values
        ObjectData data = new ObjectData();
        data.setColor(faker.color().name());
        data.setCapacity(faker.number().numberBetween(64, 512) + " GB");

        // Create an ObjectPayload instance and set the name and the data
        ObjectPayload payload = new ObjectPayload();
        payload.setName(faker.commerce().productName());
        payload.setData(data);

        return payload;
    }

    // Generates a random payload for a patch request
    public static ObjectPayload generatePatchObjectPayload() {
        // Create an ObjectPayload instance and set a random name
        ObjectPayload payload = new ObjectPayload();
        payload.setName(faker.commerce().productName());

        return payload;
    }
}
