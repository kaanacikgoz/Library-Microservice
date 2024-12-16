package com.acikgozkaan.borrowing_service.core;

import com.acikgozkaan.borrowing_service.core.exception.BorrowingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BorrowingNotFoundException.class)
    public ResponseEntity<String> handleBorrowingNotFoundException(BorrowingNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
