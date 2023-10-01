package io.juanqui.userservice.controller;

import io.juanqui.userservice.dto.PostDTO;
import io.juanqui.userservice.dto.UserDTO;
import io.juanqui.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private IUserService userService;


    @GetMapping("/posts/{userId}")
    public ResponseEntity<UserDTO> getUserWithPosts(@PathVariable("userId") Long userId){
        try {
            var userDTO  =  userService.getUserWithPosts(userId);
            return ResponseEntity.ok(userDTO);
        } catch (ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(503)).build();

        }
    }
}
