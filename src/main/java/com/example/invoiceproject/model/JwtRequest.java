package com.example.invoiceproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtRequest {

        private String username;

        private String password;

}

