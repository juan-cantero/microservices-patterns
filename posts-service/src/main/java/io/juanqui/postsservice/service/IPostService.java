package io.juanqui.postsservice.service;

import io.juanqui.postsservice.model.Post;

import java.util.List;

public interface IPostService {
    public List<Post> getPostsByUserId(Long userId);
}
