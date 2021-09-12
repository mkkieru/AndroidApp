package com.example.prettyalphas.models;

public class Property {
    /*
    CREATE TABLE properties(
    id SERIAL PRIMARY KEY,
    type VARCHAR,
    location VARCHAR,
    description VARCHAR,
    value INT);*/

    private int id;
    private String type;
    private String location;
    private String description;
    private Integer value;
    private String propertyImage;

    public Property(String type, String location, String description, Integer value, String propertyImage) {
        this.type = type;
        this.location = location;
        this.description = description;
        this.value = value;
        this.propertyImage = propertyImage;
    }

    public String getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Integer getValue() {
        return value;
    }
}
