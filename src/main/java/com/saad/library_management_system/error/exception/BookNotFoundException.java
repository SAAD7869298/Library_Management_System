package com.saad.library_management_system.error.exception;

import lombok.Getter;

public class BookNotFoundException extends RuntimeException {

    public static final int STATUS_CODE = 404;
    public static final String MESSAGE = "Book_Not_Found";
    @Getter
    private final String description;

    public BookNotFoundException(String description) {
        super(description);
        this.description = description;
    }
}
