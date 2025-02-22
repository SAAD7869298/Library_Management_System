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
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    private BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook(BookDto bookDto) {
        Book book = BookTransformation.toBook(bookDto);
        bookRepository.save(book);
       log.info("Book saved:  " + book.getBookId());
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookTransformation::toBookDto)
                .toList();
    }

    public BookDto getBookById(UUID bookId) {
        return bookRepository.findById(bookId)
                .map(BookTransformation::toBookDto)
                .orElseThrow(() -> {
            log.info("Book not found: {}", bookId);
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        });
    }

    public void updateBook(UUID bookId, BookDto bookDto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    throw new BookNotFoundException("Book with ID " + bookId + " not found");
                });

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        book.setPublishedDate(bookDto.getPublishedDate());
        book.setIsbn(bookDto.getIsbn());
        book.setCategory(bookDto.getCategory());
        book.setQuantity(bookDto.getQuantity());
        book.setAvailableQuantity(bookDto.getAvailableQuantity());

        bookRepository.save(book);
    }

    public void deleteBook(UUID bookId) {
        bookRepository.findById(bookId).ifPresentOrElse(book -> {
            bookRepository.delete(book);
            log.info("Book deleted: {}", bookId);
        } , () -> new BookNotFoundException("Book with ID " + bookId + " not found"));
    }
}

