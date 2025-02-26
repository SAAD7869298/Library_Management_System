package com.saad.library_management_system.service;

import com.saad.library_management_system.error.exception.BookNotFoundException;
import com.saad.library_management_system.model.dto.BookDto;
import com.saad.library_management_system.model.entity.Book;
import com.saad.library_management_system.repository.BookRepository;
import com.saad.library_management_system.util.transformation.BookTransformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BookServiceImplement implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    private BookServiceImplement(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveBook(BookDto bookDto) {
        Book book = BookTransformation.toBook(bookDto);
        bookRepository.save(book);
        log.info("Book saved:  " + book.getBookId());
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookTransformation::toBookDto)
                .toList();
    }

    @Override
    public BookDto getBookById(UUID bookId) {
        return bookRepository.findById(bookId)
                .map(BookTransformation::toBookDto)
                .orElseThrow(() -> {
                    log.info("Book not found: {}", bookId);
                    throw new BookNotFoundException("Book with ID " + bookId + " not found");
                });
    }

    @Override
    public void updateBook(UUID bookId, BookDto bookDto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    throw new BookNotFoundException("Book with ID " + bookId + " not found");
                });
        book = Book.builder()
                .bookId(bookId)
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .publishedDate(bookDto.getPublishedDate())
                .isbn(bookDto.getIsbn())
                .category(bookDto.getCategory())
                .quantity(bookDto.getQuantity())
                .availableQuantity(bookDto.getAvailableQuantity())
                .build();
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(UUID bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));
        bookRepository.delete(book);
    }
}
