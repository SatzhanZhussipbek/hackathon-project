package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetails {

    private String id;

    private String dueDate;

    private String clientName;

    private double price;

    private InvoiceStatus status;


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

