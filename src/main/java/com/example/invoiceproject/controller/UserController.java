package com.example.invoiceproject.controller;

import com.example.invoiceproject.model.JwtRequest;
import com.example.invoiceproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateData (@RequestBody JwtRequest jwtRequest) throws Exception {
        return ResponseEntity.ok(userService.authenticateUser(jwtRequest.getUsername(),
                jwtRequest.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JwtRequest jwtRequest) {
        return ResponseEntity.ok(userService.register(jwtRequest.getUsername(), jwtRequest.getPassword()));
    }
}
