package com.github.vlad.netetskyi.services;

import com.github.vlad.netetskyi.repositories.PostRepository;
import com.github.vlad.netetskyi.repositories.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post getRandomPost() {
        List<Post> allPosts = (List<Post>) postRepository.findAll();
        int randomIndex = new Random().nextInt(allPosts.size());
        return allPosts.get(randomIndex);
    }
}
