package com.example.fullstackbackend.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
         super("Could not find the use with id: " + id);
    }
}
