package io.juanqui.postsservice.service;

import io.juanqui.postsservice.model.Post;
import io.juanqui.postsservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findPostByUserId(userId);
    }
}
