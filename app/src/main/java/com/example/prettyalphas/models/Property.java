package com.example.prettyalphas.models;

import org.parceler.Parcel;

@Parcel
public class Property {

     int id;
     String type;
     String location;
     String description;
     Integer value;
     String propertyImage;
     String pushId;


    public Property() {}

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
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
