package com.example.invoiceproject.entity;

import com.example.invoiceproject.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Target;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDetails {

    private String id;

    private String dueDate;

    private String clientName;

    private double price;

    private String status;

    public InvoiceDetails(String id, String dueDate, String clientName,
                          double price, String status) {
        this.id = id;
        this.dueDate = dueDate;
        this.clientName = clientName;
        this.price = price;
        this.status = status;
    }

}

