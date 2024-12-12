package com.acikgozkaan.borrowing_service.repository;

import com.acikgozkaan.borrowing_service.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}
