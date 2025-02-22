package com.saad.library_management_system.service;

import com.saad.library_management_system.error.exception.BorrowNotFoundException;
import com.saad.library_management_system.model.dto.BorrowDto;
import com.saad.library_management_system.model.entity.Borrow;
import com.saad.library_management_system.repository.BorrowRepository;
import com.saad.library_management_system.util.transformation.BookTransformation;
import com.saad.library_management_system.util.transformation.BorrowTransformation;
import com.saad.library_management_system.util.transformation.UserTransformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BorrowService {

    private BorrowRepository borrowRepository;

    @Autowired
    private BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public void saveBorrow(BorrowDto borrowDto) {
        Borrow borrow = BorrowTransformation.toBorrow(borrowDto);
        borrowRepository.save(borrow);
        log.info("Successfully saved borrow record with ID: {} ", borrow.getBorrowId());
    }

    public List<BorrowDto> getAllBorrows() {
        return borrowRepository.findAll().stream()
                .map(BorrowTransformation::toBorrowDto)
                .toList();
    }

    public BorrowDto findBorrowById(UUID borrowId) {
        return borrowRepository.findById(borrowId)
                .map(BorrowTransformation::toBorrowDto)
                .orElseThrow(() -> new BorrowNotFoundException("No borrow records found for book with ID: " + borrowId));
    }

    public void updateBorrow(UUID borrowId, BorrowDto borrowDto) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow record with ID " + borrowId + " not found"));

        borrow.setBorrowDate(borrowDto.getBorrowDate());
        borrow.setDueDate(borrowDto.getDueDate());
        borrow.setReturnDate(borrowDto.getReturnDate());
//        borrow.setBook(BookTransformation.toBook(borrowDto.getBook()));
//        borrow.setUser(UserTransformation.toUser(borrowDto.getUser()));

        borrowRepository.save(borrow);

        log.info("Successfully updated borrow record with ID: {} ", borrowId);
    }


    public void deleteBorrow(UUID borrowId) {
        borrowRepository.findById(borrowId).ifPresentOrElse(borrow -> {
            borrowRepository.delete(borrow);
            log.info("Successfully deleted borrow record with ID: {}", borrowId);
        }, () -> new BorrowNotFoundException("Borrow with ID " + borrowId + " not found"));
    }

    // Custom method to get borrow records by user ID
    public List<BorrowDto> findByUserId(UUID userId) {

        /*return borrowRepository.findByUserId(userId).get().stream()
                .map(BorrowTransformation::toBorrowDto)
                .toList();*/
        return borrowRepository.findByUserId(userId).orElseThrow(() -> new BorrowNotFoundException("No borrow records found for user with ID: " + userId))
                .stream().map(BorrowTransformation::toBorrowDto)
                .toList();
    }

    public List<BorrowDto> findByBookId(UUID bookId) {
        return borrowRepository.findByBookId(bookId).orElseThrow(() -> new BorrowNotFoundException("No borrow records found for user with ID: " + bookId))
                .stream().map(BorrowTransformation::toBorrowDto)
                .toList();
    }

//    public BorrowDto findByBookAndUser(UUID bookId, UUID userId) {
//        return borrowRepository.findByBookIdAndUserId(bookId, userId)
//                .map(BorrowTransformation::toBorrowDto)
//                .orElseThrow(() -> new BorrowNotFoundException("No borrow record found for user with ID: " + userId + " and book with ID: " + bookId));
//    }
}