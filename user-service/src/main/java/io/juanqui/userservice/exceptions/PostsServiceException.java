package io.juanqui.userservice.exceptions;

public class PostsServiceException extends RuntimeException {
    public PostsServiceException(String message) {
        super(message);
    }
}
