package com.example.VisaPrep.Security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TEMPORARY: Return a hardcoded user
        if (username.equals("testuser")) {
            return new User("testuser", "password", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
