package com.example.VisaPrep;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionServiceTest {

    @Test
    public void testGetAllTransactions() {
        // Arrange: Mock repository
        TransactionRepository mockRepo = Mockito.mock(TransactionRepository.class);
        TransactionService transactionService = new TransactionService(mockRepo);

        Transaction t1 = new Transaction(); // Add values if needed
        Transaction t2 = new Transaction();

        List<Transaction> dummyList = Arrays.asList(t1, t2);
        Mockito.when(mockRepo.findAll()).thenReturn(dummyList);

        // Act: Call service method
        List<Transaction> result = transactionService.getAllTransactions();

        // Assert: Verify result
        assertEquals(2, result.size());
    }
}
