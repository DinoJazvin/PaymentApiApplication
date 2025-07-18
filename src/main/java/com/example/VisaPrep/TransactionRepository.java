package com.example.VisaPrep;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByIdempotencyKey(String IdempotencyKey);

    Optional<Transaction> findByAmountGreaterThanAndTimestampAfter(BigDecimal amount, LocalDateTime timestamp);
}
