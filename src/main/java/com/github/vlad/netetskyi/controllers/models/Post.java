package com.github.vlad.netetskyi.controllers.models;

import org.springframework.web.multipart.MultipartFile;

public class Post {
    private String description;
    // private byte[] img;
    private MultipartFile img;

    public Post(String description, MultipartFile img) {
        this.description = description;
        this.img = img;
    }

    public Post() {
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
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
