package com.saad.library_management_system.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotNull(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotNull(message = "Author is required")
    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    private String author;

    @Size(min = 1, max = 255, message = "Publisher name must be less than 255 characters")
    private String publisher;

    private Date publishedDate;

    @NotNull(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @Pattern(regexp = "^(\\d{9}[0-9X])|(\\d{13})$", message = "Invalid ISBN format")
    private String isbn;

    @Size(min = 1, max = 255, message = "Category name must be less than 255 characters")
    private String category;

    @NotNull(message = "Quantity is required")
    private int quantity;

    @NotNull(message = "Available quantity is required")
    private int availableQuantity;

}
