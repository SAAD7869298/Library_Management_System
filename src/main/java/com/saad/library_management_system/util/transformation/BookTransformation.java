package com.saad.library_management_system.util.transformation;

import com.saad.library_management_system.model.dto.BookDto;
import com.saad.library_management_system.model.entity.Book;
import com.saad.library_management_system.util.Utils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookTransformation {

    public BookTransformation() {
    }

    public static Book toBook(BookDto bookDto) {
        return Book.builder()
                .bookId(Utils.getRandomInt())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .publishedDate(bookDto.getPublishedDate())
                .isbn(bookDto.getIsbn())
                .category(bookDto.getCategory())
                .quantity(bookDto.getQuantity())
                .availableQuantity(bookDto.getAvailableQuantity())
                .build();
    }

    public static BookDto toBookDto(Book book) {
        List<UUID> borrowIds = book.getBorrows().stream()
                .map(borrow -> borrow.getBorrowId())
                .collect(Collectors.toList());

        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .isbn(book.getIsbn())
                .category(book.getCategory())
                .quantity(book.getQuantity())
                .availableQuantity(book.getAvailableQuantity())
                .borrowIds(borrowIds)
                .build();
    }
}
