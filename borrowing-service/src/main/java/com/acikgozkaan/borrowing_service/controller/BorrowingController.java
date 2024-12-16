package com.acikgozkaan.borrowing_service.controller;

import com.acikgozkaan.borrowing_service.entity.Borrowing;
import com.acikgozkaan.borrowing_service.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/users/{userId}/books/{bookId}")
    public ResponseEntity<Borrowing> createBorrowing(@PathVariable String userId, @PathVariable Long bookId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingService.createBorrowing(userId, bookId));
    }

    @GetMapping
    public ResponseEntity<List<Borrowing>> getAllBorrowing() {
        return ResponseEntity.ok(borrowingService.getAllBorrowing());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrowing> getBorrowingById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.getBorrowingById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Borrowing> deleteBorrowing(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.deleteBorrowing(id));
    }

}
