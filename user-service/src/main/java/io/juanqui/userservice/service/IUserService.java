package io.juanqui.userservice.service;

import io.juanqui.userservice.dto.UserDTO;

import javax.naming.ServiceUnavailableException;

public interface IUserService {
    public UserDTO getUserWithPosts(Long userId) throws ServiceUnavailableException;

}
