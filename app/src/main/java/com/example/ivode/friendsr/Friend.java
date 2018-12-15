package com.example.ivode.friendsr;

import java.io.Serializable;

public class Friend implements Serializable {
    private String name, description;
    private int image_id;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return image_id;
    }

    public Friend(String name, String description, int image_id) {
        this.name = name;
        this.description = description;
        this.image_id = image_id;
    }
}
