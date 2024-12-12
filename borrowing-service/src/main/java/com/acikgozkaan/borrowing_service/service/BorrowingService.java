package com.acikgozkaan.borrowing_service.service;

import com.acikgozkaan.book_service.core.exception.BookNotFoundException;
import com.acikgozkaan.book_service.entity.Book;
import com.acikgozkaan.borrowing_service.core.exception.BorrowingNotFoundException;
import com.acikgozkaan.borrowing_service.core.feignClient.BookServiceClient;
import com.acikgozkaan.borrowing_service.core.feignClient.UserServiceClient;
import com.acikgozkaan.borrowing_service.entity.Borrowing;
import com.acikgozkaan.borrowing_service.entity.Status;
import com.acikgozkaan.borrowing_service.repository.BorrowingRepository;
import com.acikgozkaan.user_service.core.exception.UserNotFoundException;
import com.acikgozkaan.user_service.entity.User;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingService {

    @Autowired
    private final BorrowingRepository borrowingRepository;

    @Autowired
    private final UserServiceClient userServiceClient;

    @Autowired
    private final BookServiceClient bookServiceClient;

    public BorrowingService(BorrowingRepository borrowingRepository, UserServiceClient userServiceClient, BookServiceClient bookServiceClient) {
        this.borrowingRepository = borrowingRepository;
        this.userServiceClient = userServiceClient;
        this.bookServiceClient = bookServiceClient;
    }

    public List<Borrowing> getAllBorrowing() {
        return borrowingRepository.findAll();
    }

    public Borrowing getBorrowingById(Long id) {
        return borrowingRepository.findById(id).orElseThrow(
                ()-> new BorrowingNotFoundException("Borrowing not found with id: "+id)
        );
    }

    @Transactional
    public Borrowing createBorrowing(String userId, Long bookId) {
        try {
            User user = userServiceClient.getUserById(userId);
        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        try {
            Book book = bookServiceClient.getBookById(bookId);
        } catch (FeignException.NotFound e) {
            throw new BookNotFoundException("Book not found with id: "+bookId);
        }

        Borrowing borrowing = Borrowing.builder()
                .userId(userId)
                .bookId(bookId)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(15))
                .status(Status.BORROWED)
                .build();

        return borrowingRepository.save(borrowing);
    }

    public Borrowing deleteBorrowing(Long id) {
        Borrowing borrowingToDelete = this.getBorrowingById(id);
        borrowingRepository.delete(borrowingToDelete);
        return borrowingToDelete;
    }

}
