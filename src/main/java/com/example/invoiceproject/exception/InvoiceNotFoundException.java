package com.example.invoiceproject.exception;

public class InvoiceNotFoundException extends RuntimeException{
     public InvoiceNotFoundException(String id) {
            super(String.format("Could not find invoice %s", id));
        }

}

