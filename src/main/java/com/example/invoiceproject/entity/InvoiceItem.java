package com.example.invoiceproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {

    private long id;

    private String itemName;

    private int quantity;

    private double price;

    private double total;

}
