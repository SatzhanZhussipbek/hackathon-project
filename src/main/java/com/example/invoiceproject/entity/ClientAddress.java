package com.example.invoiceproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ClientAddress {

    private String clientStreet;

    private String clientCity;

    private String clientCode;

    private String clientCountry;
}
