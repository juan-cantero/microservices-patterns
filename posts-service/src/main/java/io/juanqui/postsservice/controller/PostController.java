package io.juanqui.postsservice.controller;

import io.juanqui.postsservice.model.Post;
import io.juanqui.postsservice.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private IPostService postService;

    @Value("${server.port}")
    private int port;
    @GetMapping("{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        System.out.println("I'm in port: " + port);
        return postService.getPostsByUserId(userId);
    }
}
