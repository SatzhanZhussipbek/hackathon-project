package com.example.invoiceproject.entity;

import com.example.invoiceproject.repository.InvoiceDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private String id = InvoiceDetails.generateRandomChars();

    private String streetAddress;

    private String city;

    private String postCode;

    private String country;

    private String clientName;

    private String clientEmail;

    private String clientAddress;

    private String clientCity;

    private String clientPost;

    private String clientCountry;

    private Date invoiceDate;

    private String paymentTerms;

    private String description;

    private List<InvoiceItem> items;

}
