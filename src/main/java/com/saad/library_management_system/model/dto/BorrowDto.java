package com.saad.library_management_system.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

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
    private UUID bookId;

    @NotNull(message = "User ID is required")
    private UUID userId;
}
