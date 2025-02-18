package com.saad.library_management_system.controller;

import com.saad.library_management_system.model.dto.BookDto;
import com.saad.library_management_system.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //http://localhost:8080/api/books/save
    @PostMapping("save")
    public void createBook(@Valid @RequestBody BookDto bookDto ) {
        bookService.saveBook(bookDto);
    }

    //http://localhost:8080/api/books/findAll
    @GetMapping("findAll")
    public List<BookDto> findAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("find/{id}")
    public BookDto findById(@PathVariable UUID id) {
        return bookService.getBookById(id);
    }

    @PutMapping("update/{id}")
    public void updateBook(@PathVariable UUID id, @Valid @RequestBody BookDto bookDto) {
        bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
    }

}
