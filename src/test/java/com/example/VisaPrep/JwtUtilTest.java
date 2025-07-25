package com.example.VisaPrep;

import com.example.VisaPrep.Security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setup() {
        jwtUtil = new JwtUtil(); // or inject if it needs dependencies
    }

    @Test
    public void testGenerateAndValidateToken() {
        String username = "testuser";

        String token = jwtUtil.generateToken(username); // or pass UserDetails mock
        assertNotNull(token);

        boolean isValid = jwtUtil.validateToken(token);
        assertTrue(isValid);
    }
}
