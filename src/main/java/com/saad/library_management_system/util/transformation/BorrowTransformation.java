package com.saad.library_management_system.util.transformation;

import com.saad.library_management_system.model.dto.BorrowDto;
import com.saad.library_management_system.model.entity.Book;
import com.saad.library_management_system.model.entity.Borrow;
import com.saad.library_management_system.model.entity.User;
import com.saad.library_management_system.util.Utils;

public class BorrowTransformation {

    public BorrowTransformation() {
    }

    public static Borrow toBorrow(BorrowDto borrowDto, Book book, User user) {
        return Borrow.builder()
                .borrowId(Utils.getRandomInt())
                .borrowDate(borrowDto.getBorrowDate())
                .dueDate(borrowDto.getDueDate())
                .returnDate(borrowDto.getReturnDate())
                .book(book)
                .user(user)
                .build();
    }

    public static BorrowDto toBorrowDto(Borrow borrow) {
        return BorrowDto.builder()
                .borrowDate(borrow.getBorrowDate())
                .dueDate(borrow.getDueDate())
                .returnDate(borrow.getReturnDate())
                .bookId(borrow.getBook().getBookId())
                .userId(borrow.getUser().getUserId())
                .build();
    }
}

