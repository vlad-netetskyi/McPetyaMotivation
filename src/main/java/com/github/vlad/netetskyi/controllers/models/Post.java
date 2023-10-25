package com.github.vlad.netetskyi.controllers.models;

public class Post {
    private String description;

    public Post(String description) {
        this.description = description;
    }

    public Post() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "description='" + description + '\'' +
                '}';
    }
}
