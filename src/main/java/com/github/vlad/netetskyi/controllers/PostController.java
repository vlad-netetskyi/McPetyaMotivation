package com.github.vlad.netetskyi.controllers;

import com.github.vlad.netetskyi.controllers.models.Post;
import com.github.vlad.netetskyi.repositories.PostRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/post")
    public String post(HttpSession session, Model model) {
        Iterable<com.github.vlad.netetskyi.repositories.models.Post> post = postRepository.findAll();
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/add")
    public String getAddPost(Model model) {
        model.addAttribute("post", new Post());
        return "post-add";
    }

    @PostMapping("/post/add")
    public String addPost(@ModelAttribute("post") Post post, Model model) {
        System.out.println("Post/add" + post.toString());
        try {
          /*  InputStream fileContent = img.getInputStream();
            byte[] fileAsByteArray = IOUtils.toByteArray(fileContent);
            System.out.println("Added photo with size = " + fileAsByteArray.length);*/
            com.github.vlad.netetskyi.repositories.models.Post dbPost = new com.github.vlad.netetskyi.repositories.models.Post(post.getDescription());
            postRepository.save(dbPost);

            return "redirect:/post";

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            throw e;
        }
    }
}


