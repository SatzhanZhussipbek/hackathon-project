package com.example.invoiceproject.entity;

import com.example.invoiceproject.repository.InvoiceDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @Column(name = "id")
    private String id = generateRandomChars();
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "payment_due")
    private String paymentDue;
    @Column(name = "payment_terms")
    private int paymentTerms;
    @Column(name = "description")
    private String description;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_email")
    private String clientEmail;
    @Column(name = "status")
    private String status;
    @Column(name = "sender_address")
    @Embedded
    private Address senderAddress;
    @Column(name = "client_address")
    @Embedded
    private ClientAddress clientAddress;
    @OneToMany(cascade=CascadeType.ALL)
    private List<InvoiceItem> items;
    @Column(name = "total")
    private double total;
    public static String generateRandomChars() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            sb.append(letters.charAt(random.nextInt(letters
                    .length())));
        }
        for (int j = 2; j < 6; j++) {
            sb.append(digits.charAt(random.nextInt(digits
                    .length())));
        }
        return sb.toString();
    }

}
