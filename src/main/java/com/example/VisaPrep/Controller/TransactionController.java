package com.example.VisaPrep.Controller;

import com.example.VisaPrep.Transaction;
import com.example.VisaPrep.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    @GetMapping("/id")
    public Optional<Transaction> getTransactionById(@PathVariable Long id){
        return transactionRepository.findById(id);
    }

    @GetMapping("/")
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/costco-recent")
    public Optional<Transaction> getRecentCostcoTransactions(@RequestParam(required = false) BigDecimal minAmount){
        if(minAmount == null || minAmount.compareTo(BigDecimal.valueOf(100)) < 0)
            minAmount = BigDecimal.valueOf(100);
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        return transactionRepository.findByAmountGreaterThanAndTimestampAfter(minAmount, thirtyDaysAgo);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        String idempotencyKey = transaction.getIdempotencyKey();

        if (idempotencyKey == null || idempotencyKey.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idempotnecy key required");
        }

        Optional<Transaction> existing = transactionRepository.findByIdempotencyKey(idempotencyKey);
        return existing.orElseGet(() -> transactionRepository.save(transaction));

    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setAmount(transactionDetails.getAmount());
                    transaction.setCurrency(transactionDetails.getCurrency());
                    transaction.setDescription(transactionDetails.getDescription());
                    transaction.setTimestamp(transactionDetails.getTimestamp());
                    return transactionRepository.save(transaction);
                })
                .orElseThrow(() -> new RuntimeException("Transaction not found with id" + id));
    }

    @DeleteMapping
    public void deleteTransaction(@PathVariable Long id){
        transactionRepository.deleteById(id);
    }

}
