package com.github.vlad.netetskyi.repositories.models;

import jakarta.persistence.*;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Lob
    @Column(length = 100000)
    private byte[] img;

    public Post(String description, byte[] img) {
        this.description = description;
        this.img = img;
    }

    public Post(String description) {
        this.description = description;
        this.img = new byte[]{};
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImg() {
        return img;
    }

    public String getBase64ImgFile() {
        System.out.println("Get image");
        if (img != null && img.length > 0) {
            byte[] encodeBase64 = Base64.encodeBase64(img, false);
            return new String(encodeBase64, StandardCharsets.UTF_8);
//            Base64.getMimeEncoder().encodeToString(byteData)
        }

        System.out.println("Error, no image for mc Petya = " + this.toString());
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(description, post.description) && Arrays.equals(img, post.img);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, description);
        result = 31 * result + Arrays.hashCode(img);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }
}
