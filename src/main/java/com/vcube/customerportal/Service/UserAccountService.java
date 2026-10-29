package com.vcube.customerportal.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vcube.customerportal.Entity.UserAccount;
import com.vcube.customerportal.Repo.UserAccountRepo;

@Service
public class UserAccountService {

    private final UserAccountRepo userAccountRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAccountService(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Registration
    public UserAccount register(UserAccount user) {

        if (userAccountRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userAccountRepo.save(user);
    }

    // Login
    public boolean login(String email, String password) {

        return userAccountRepo.findByEmail(email)
                .map(user -> passwordEncoder.matches(
                        password, user.getPassword()))
                .orElse(false);
    }
}