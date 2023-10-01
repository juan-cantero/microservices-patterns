package io.juanqui.userservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.juanqui.userservice.client.PostsClient;
import io.juanqui.userservice.dto.PostDTO;
import io.juanqui.userservice.dto.UserDTO;
import io.juanqui.userservice.exceptions.PostsServiceException;
import io.juanqui.userservice.exceptions.UserNotFoundException;
import io.juanqui.userservice.model.User;
import io.juanqui.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostsClient postsClient;
    @Override
    @CircuitBreaker(name = "postservice", fallbackMethod = "getUserWithPostsFallback")
    @Retry(name = "postservice")
    public UserDTO getUserWithPosts(Long userId) throws ServiceUnavailableException {
        var userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            try {
                List<PostDTO> posts = postsClient.getPostsByUserId(userId);

                return UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .lastname(user.getLastname())
                        .phone(user.getPhone())
                        .posts(posts)
                        .build();
            } catch (PostsServiceException e) {
                // Handle the case where the posts service is down or fails
                // You can log the error or take appropriate actions
                throw new ServiceUnavailableException("Posts service is unavailable.");
            }
        } else {
            // Handle the case where the user is not found
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }

    public UserDTO getUserWithPostsFallback(Throwable t) {
        return UserDTO.builder()
                .name("fallback")
                .lastname("fallback")
                .phone("fallback")
                .posts(List.of())
                .build();
    }

   
}
