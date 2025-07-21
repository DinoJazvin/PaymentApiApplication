package com.example.VisaPrep;  // Use your actual package name here

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TransactionControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetCostcoRecentTransactions_ReturnsOk() {
        ResponseEntity<String> response = restTemplate.getForEntity("/transactions/costco-recent", String.class);

        // Verify HTTP 200 OK
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        // Optional: print response body for debugging
        System.out.println(response.getBody());
    }
}
