package io.juanqui.userservice.client;

import io.juanqui.userservice.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "postservice")
public interface PostsClient {

    @GetMapping("/posts/{userId}")
    public List<PostDTO> getPostsByUserId(@PathVariable("userId") Long userId);

}
