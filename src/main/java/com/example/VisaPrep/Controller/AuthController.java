package com.example.VisaPrep.Controller;

import com.example.VisaPrep.Security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        // NOTE: Hardcoding a simple check for now:
        if ("costco".equals(username) && "secret".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token); //200 status
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); //401 code
        }
    }
}
