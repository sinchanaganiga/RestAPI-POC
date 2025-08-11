package com.example.api.pojo;

public class ObjectPayload {
    private String name;
    private ObjectData data;  // This is the nested ObjectData

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
    	
        this.name = name;
    }

    public ObjectData getData() {
        return data;
    }

    public void setData(ObjectData data) {
        this.data = data;
    }
}

