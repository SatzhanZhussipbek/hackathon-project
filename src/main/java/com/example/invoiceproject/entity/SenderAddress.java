package com.example.invoiceproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SenderAddress {

    private String senderStreet;

    private String senderCity;

    private String senderPostCode;

    private String senderCountry;
}
