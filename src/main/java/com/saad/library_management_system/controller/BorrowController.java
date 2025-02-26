package com.saad.library_management_system.controller;

import com.saad.library_management_system.model.dto.BorrowDto;
import com.saad.library_management_system.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("save")
    public void createBorrow(@RequestBody BorrowDto borrowDto) {
        borrowService.saveBorrow(borrowDto);
    }

    @GetMapping("findAll")
    public List<BorrowDto> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("/{borrowId}")
    public BorrowDto getBorrowById(@PathVariable UUID borrowId) {
        return borrowService.findBorrowById(borrowId);
    }

    @PutMapping("/{borrowId}")
    public void updateBorrow(@PathVariable UUID borrowId, @RequestBody BorrowDto borrowDto) {
        borrowService.updateBorrow(borrowId, borrowDto);
    }

    @PutMapping("/return")
    public void returnBorrow(@RequestParam String bookTitle, @RequestParam String username) {
        borrowService.returnBook(bookTitle, username);
    }
//***
    @GetMapping("/user/{userId}")
    public List<BorrowDto> getBorrowsByUserId(@PathVariable UUID userId) {
        return borrowService.getAllBorrowsByUser(userId);
    }
//***
    @GetMapping("/book/{bookId}")
    public List<BorrowDto> getBorrowsByBookId(@PathVariable UUID bookId) {
        return borrowService.getAllBorrowsByBook(bookId);
    }

    @DeleteMapping("/{borrowId}")
    public void deleteBorrow(@PathVariable UUID borrowId) {
        borrowService.deleteBorrow(borrowId);
    }
}


