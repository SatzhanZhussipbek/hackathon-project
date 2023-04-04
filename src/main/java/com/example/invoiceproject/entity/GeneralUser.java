package com.example.invoiceproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralUser {

    private long id;

    private String fullName;

    private String streetAddress;

    private String city;

    private String postCode;

    private String country;

    private List<Invoice> invoices = new ArrayList<>();
}
