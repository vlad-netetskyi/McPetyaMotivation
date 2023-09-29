package org.example.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.example.models.Post;
import org.example.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class PostController {
    @Autowired(required = false)                                      // для чого це?
    private PostRepository postRepository;

    @GetMapping("/post")
    public String post(HttpSession session, Model model) {

        Iterable<Post> post = postRepository.findAll();
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/add")
    public String getAddPost(Model model) {
        return "post-add";
    }

    @PostMapping("/post/add")
    public String addPost(@RequestParam String description, @RequestParam Part img, Model mod) throws IOException {
        InputStream fileContent = img.getInputStream();
        byte[] fileAsByteArray = IOUtils.toByteArray(fileContent);
        System.out.println("Added photo with size = " + fileAsByteArray.length);
        Post post = new Post(description, fileAsByteArray);
        postRepository.save(post);
        return "redirect:/post";
    }
}


