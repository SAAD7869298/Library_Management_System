package com.saad.library_management_system.util.transformation;

import com.saad.library_management_system.model.dto.BorrowDto;
import com.saad.library_management_system.model.entity.Borrow;
import com.saad.library_management_system.util.Utils;

public class BorrowTransformation {

    public BorrowTransformation() {
    }

    public static Borrow toBorrow(BorrowDto borrowDto) {
        return Borrow.builder()
                .borrowId(Utils.getRandomInt())
                .borrowDate(borrowDto.getBorrowDate())
                .dueDate(borrowDto.getDueDate())
                .returnDate(borrowDto.getReturnDate())
                .book(BookTransformation.toBook(borrowDto.getBook()))
                .user(UserTransformation.toUser(borrowDto.getUser()))
                .build();
    }

    public static BorrowDto toBorrowDto(Borrow borrow) {
        return BorrowDto.builder()
                .borrowDate(borrow.getBorrowDate())
                .dueDate(borrow.getDueDate())
                .returnDate(borrow.getReturnDate())
                .book(BookTransformation.toBookDto(borrow.getBook()))
                .user(UserTransformation.toUserDto(borrow.getUser()))
                .build();
    }
}

