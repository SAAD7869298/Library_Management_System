package com.saad.library_management_system.error.exception;

import lombok.Getter;

public class UserNotFoundException extends RuntimeException {
    public static final int STATUS_CODE = 404;
    public static final String MESSAGE = "user_Not_Found";
    @Getter
    private final String description;

    public UserNotFoundException(String description) {
        super(description);
        this.description = description;
    }

    /*public UserNotFoundException(String message) {
        super(message);
    }*/
}
