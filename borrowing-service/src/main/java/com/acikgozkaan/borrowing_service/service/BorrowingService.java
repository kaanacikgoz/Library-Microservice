package com.acikgozkaan.borrowing_service.service;

import com.acikgozkaan.borrowing_service.core.exception.BorrowingNotFoundException;
import com.acikgozkaan.borrowing_service.core.feignClient.BookServiceClient;
import com.acikgozkaan.borrowing_service.core.feignClient.UserServiceClient;
import com.acikgozkaan.borrowing_service.dto.response.FeignBookResponse;
import com.acikgozkaan.borrowing_service.dto.response.FeignUserResponse;
import com.acikgozkaan.borrowing_service.entity.Borrowing;
import com.acikgozkaan.borrowing_service.entity.Status;
import com.acikgozkaan.borrowing_service.repository.BorrowingRepository;
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
            FeignUserResponse userResponse = userServiceClient.getUserById(userId);
        } catch (FeignException.NotFound e) {
            throw new BorrowingNotFoundException("User not found with id: " + userId);
        }

        try {
            FeignBookResponse bookResponse = bookServiceClient.getBookById(bookId);
        } catch (FeignException.NotFound e) {
            throw new BorrowingNotFoundException("Book not found with id: "+bookId);
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
