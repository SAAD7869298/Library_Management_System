package com.saad.library_management_system.service;

import com.saad.library_management_system.model.dto.BookDto;

import java.util.List;
import java.util.UUID;


public interface BookService {

    public void saveBook(BookDto bookDto);

    public List<BookDto> getAllBooks();

    public BookDto getBookById(UUID bookId) ;

    public void updateBook(UUID bookId, BookDto bookDto) ;

    public void deleteBook(UUID bookId) ;
}

