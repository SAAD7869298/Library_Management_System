package com.saad.library_management_system.error.exception;

import lombok.Getter;

public class BorrowNotFoundException extends RuntimeException {
    public static final int STATUS_CODE = 404;
    public static final String MESSAGE = "borrow_not_found";
    @Getter
    private final String description;

    public BorrowNotFoundException(String description) {
        super(description);
        this.description = description;
    }
}
