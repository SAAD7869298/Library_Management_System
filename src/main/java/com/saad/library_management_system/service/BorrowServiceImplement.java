package com.saad.library_management_system.service;

import com.saad.library_management_system.error.exception.BookNotAvailableException;
import com.saad.library_management_system.error.exception.BookNotFoundException;
import com.saad.library_management_system.error.exception.BorrowNotFoundException;
import com.saad.library_management_system.error.exception.UserNotFoundException;
import com.saad.library_management_system.model.dto.BorrowDto;
import com.saad.library_management_system.model.entity.Book;
import com.saad.library_management_system.model.entity.Borrow;
import com.saad.library_management_system.model.entity.User;
import com.saad.library_management_system.repository.BookRepository;
import com.saad.library_management_system.repository.BorrowRepository;
import com.saad.library_management_system.repository.UserRepository;
import com.saad.library_management_system.util.transformation.BorrowTransformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BorrowServiceImplement implements BorrowService  {

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private BorrowRepository borrowRepository;

    @Autowired
    private BorrowServiceImplement(BookRepository bookRepository, UserRepository userRepository, BorrowRepository borrowRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
    }

    @Override
    public void saveBorrow(BorrowDto borrowDto) {
        Book book = bookRepository.findById(borrowDto.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        if (book.getAvailableQuantity() <= 0) {
            throw new BookNotAvailableException("Book available quantity is less than zero");
        }

        User user = userRepository.findById(borrowDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Borrow borrow = BorrowTransformation.toBorrow(borrowDto, book, user);

        borrowRepository.save(borrow);
        log.info("Successfully saved borrow record with ID: {} ", borrow.getBorrowId());

        // Update the available quantity of the book
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);

        // Log the borrow action
        log.info("Book {} borrowed by user {}", book.getTitle(), user.getUsername());
    }
    @Override
    public List<BorrowDto> getAllBorrows() {
        return borrowRepository.findAll().stream()
                .map(BorrowTransformation::toBorrowDto)
                .toList();
    }
    @Override
    public BorrowDto findBorrowById(UUID borrowId) {
        return borrowRepository.findById(borrowId)
                .map(BorrowTransformation::toBorrowDto)
                .orElseThrow(() -> new BorrowNotFoundException("No borrow records found for book with ID: " + borrowId));
    }
    @Override
    public void updateBorrow(UUID borrowId, BorrowDto borrowDto) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow record with ID " + borrowId + " not found"));

        Book book = bookRepository.findById(borrowDto.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        User user = userRepository.findById(borrowDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (book.getAvailableQuantity() <= 0) {
            throw new BookNotAvailableException("Book is not available for borrowing");
        }


        borrow.setBorrowDate(borrowDto.getBorrowDate());
        borrow.setDueDate(borrowDto.getDueDate());
        borrow.setReturnDate(borrowDto.getReturnDate());
        borrow.setUser(user);
        borrow.setBook(book);

        borrowRepository.save(borrow);

        log.info("Successfully updated borrow record with ID: {} ", borrowId);
    }
    @Override
    public void deleteBorrow(UUID borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow record with ID " + borrowId + " not found"));

        borrowRepository.delete(borrow);
        log.info("Successfully deleted borrow record with ID: {}", borrowId);
    }
    @Override
    public List<BorrowDto> getAllBorrowsByUser(UUID userId) {
        List<Borrow> borrows = borrowRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new UserNotFoundException("No borrow records found for user with ID: " + userId));

        return borrows.stream().map(BorrowTransformation::toBorrowDto)
                .toList();
    }
    @Override
    public List<BorrowDto> getAllBorrowsByBook(UUID bookId) {
        return borrowRepository.findByBook_BookId(bookId)
                .orElseThrow(() -> new BorrowNotFoundException("No borrow records found for user with ID: " + bookId))
                .stream().map(BorrowTransformation::toBorrowDto)
                .toList();
    }

    // Return a borrowed book
    @Override
    public void returnBook(String bookTitle, String username){

        Book book = bookRepository.findByTitle(bookTitle)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BorrowNotFoundException("User not found"));

        Borrow borrow = borrowRepository.findByBook_BookIdAndUser_UserId(book.getBookId(), user.getUserId())
                .orElseThrow(() -> new BorrowNotFoundException("This book is not borrowed by the user"));

        borrow.setReturnDate(new Date());  // Set the return date as current date
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        bookRepository.save(book);
        borrowRepository.save(borrow);
        log.info("Book '{}' returned by user '{}'", book.getTitle(), user.getUsername());
    }
}
