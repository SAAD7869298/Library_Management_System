package com.saad.library_management_system.service;

import com.saad.library_management_system.model.dto.BorrowDto;

import java.util.List;
import java.util.UUID;

public interface BorrowService {


    public void saveBorrow(BorrowDto borrowDto) ;

    public List<BorrowDto> getAllBorrows() ;

    public BorrowDto findBorrowById(UUID borrowId) ;

    public void updateBorrow(UUID borrowId, BorrowDto borrowDto) ;

    public void deleteBorrow(UUID borrowId) ;

    public List<BorrowDto> getAllBorrowsByUser(UUID userId) ;

    public List<BorrowDto> getAllBorrowsByBook(UUID bookId) ;

    // Return a borrowed book
    public void returnBook(String bookTitle, String username);
}