package com.example.invoiceproject.exception;

public class InvoicesNotFoundException extends RuntimeException {
    public InvoicesNotFoundException(Long id) {
        super(String.format("Could not find invoices under this client id: %d", id));
    }
}
