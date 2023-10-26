package com.github.vlad.netetskyi.controllers;

import com.github.vlad.netetskyi.controllers.models.Post;
import com.github.vlad.netetskyi.repositories.PostRepository;
import com.github.vlad.netetskyi.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String post(HttpSession session, Model model) {
       com.github.vlad.netetskyi.repositories.models.Post randomPost = postService.getRandomPost();
        model.addAttribute("post", randomPost);
        return "post";
    }

    @GetMapping("/post/add")
    public String getAddPost(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("img", null);
        return "post-add";
    }

    @PostMapping("/post/add")
    public String addPost(@ModelAttribute("post") Post post, Model model) throws IOException {
        System.out.println("Post/add" + post.toString());
        try {
            InputStream fileContent = post.getImg().getInputStream();
            byte[] fileAsByteArray = IOUtils.toByteArray(fileContent);
            System.out.println("Added photo with size = " + fileAsByteArray.length);
            com.github.vlad.netetskyi.repositories.models.Post dbPost = new com.github.vlad.netetskyi.repositories.models.Post(post.getDescription(), fileAsByteArray);
            postRepository.save(dbPost);

            return "redirect:/post";

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            throw e;
        }

    }
    @GetMapping("/")
    public String showRandomPost(Model model) {
        com.github.vlad.netetskyi.repositories.models.Post randomPost = postService.getRandomPost();
        model.addAttribute("post", randomPost);
        return "post";
    }
}


