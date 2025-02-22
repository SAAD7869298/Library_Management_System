package com.saad.library_management_system.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto {

    @NotNull(message = "Borrow date is required")
    private Date borrowDate;

    @NotNull(message = "Due date is required")
    private Date dueDate;

    private Date returnDate;

    @NotNull(message = "Book ID is required")
    private BookDto book;

    @NotNull(message = "User ID is required")
    private UserDto user;
}
