package com.vcube.customerportal.Controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.customerportal.Entity.UserAccount;
import com.vcube.customerportal.Service.UserAccountService;

@RestController
@RequestMapping("/auth")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    // Registration API
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAccount user) {

        if (user.getName() == null || user.getName().isBlank()
                || user.getEmail() == null || user.getEmail().isBlank()
                || user.getPassword() == null || user.getPassword().isBlank()) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message",
                            "Name, email and password are required"));
        }

        try {
            UserAccount savedUser = userAccountService.register(user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Registration successful",
                            "id", savedUser.getId(),
                            "name", savedUser.getName(),
                            "email", savedUser.getEmail()));

        } catch (IllegalArgumentException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message",
                            "Email and password are required"));
        }

        boolean valid = userAccountService.login(email, password);

        if (valid) {
            return ResponseEntity.ok(
                    Map.of("message", "Login successful"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }
}