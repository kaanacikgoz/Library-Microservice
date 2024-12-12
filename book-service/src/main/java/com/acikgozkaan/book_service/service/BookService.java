package com.acikgozkaan.book_service.service;

import com.acikgozkaan.book_service.core.exception.BookNotFoundException;
import com.acikgozkaan.book_service.dto.BookSaveRequest;
import com.acikgozkaan.book_service.dto.BookUpdateRequest;
import com.acikgozkaan.book_service.entity.Book;
import com.acikgozkaan.book_service.entity.Category;
import com.acikgozkaan.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(BookSaveRequest bookSaveRequest) {
        Category category = Category.fromString(bookSaveRequest.getCategory().name());

        Book bookToSave = Book.builder()
                .title(bookSaveRequest.getTitle())
                .author(bookSaveRequest.getAuthor())
                .category(category)
                .stock(bookSaveRequest.getStock())
                .build();

        return bookRepository.save(bookToSave);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("Book not found with id: " + id)
        );
    }

    public Book updateBook(Long id, BookUpdateRequest bookUpdateRequest) {
        Book bookToUpdate = this.getBookById(id);
        Category category = Category.fromString(bookUpdateRequest.getCategory().name());

        bookToUpdate.setTitle(bookUpdateRequest.getTitle());
        bookToUpdate.setAuthor(bookUpdateRequest.getAuthor());
        bookToUpdate.setCategory(category);
        bookToUpdate.setStock(bookUpdateRequest.getStock());

        return bookRepository.save(bookToUpdate);
    }

    public Book deleteBook(Long id) {
        Book bookToDelete = this.getBookById(id);
        bookRepository.delete(bookToDelete);
        return bookToDelete;
    }

}
