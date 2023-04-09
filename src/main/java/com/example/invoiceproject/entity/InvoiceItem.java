package com.example.invoiceproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Invoice_item")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "total")
    private double total;

    public InvoiceItem(String name, int quantity, double price, double total) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
}
