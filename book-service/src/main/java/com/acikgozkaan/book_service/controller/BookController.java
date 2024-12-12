package com.acikgozkaan.book_service.controller;

import com.acikgozkaan.book_service.dto.BookSaveRequest;
import com.acikgozkaan.book_service.dto.BookUpdateRequest;
import com.acikgozkaan.book_service.entity.Book;
import com.acikgozkaan.book_service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody @Valid BookSaveRequest bookSaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookSaveRequest));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return  ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return  ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequest bookUpdateRequest) {
        return ResponseEntity.ok(bookService.updateBook(id, bookUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

}
