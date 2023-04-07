package com.example.invoiceproject.service;

import com.example.invoiceproject.entity.*;
import com.example.invoiceproject.exception.InvoiceNotFoundException;
import com.example.invoiceproject.repository.InvoiceCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    InvoiceCrudRepository invoiceCrudRepository;

    public Invoice getInvoice (String invoiceId) {
        if (invoiceCrudRepository.getInvoiceById(invoiceId) != null) {
            return invoiceCrudRepository.getInvoiceById(invoiceId);
        }
        throw new InvoiceNotFoundException(invoiceId);
    }

    public void updateStatus(InvoiceStatus status, long id) {
        invoiceCrudRepository.updateInvoiceStatus(status, id);
    }

    public void create(long invoiceId, String createdAt, String paymentDue, int paymentTerms, String description,
                       String clientName, String clientEmail, InvoiceStatus status,
                       SenderAddress senderAddress, ClientAddress clientAddress,
                       List<InvoiceItem> items, double total) {
        if (invoiceCrudRepository.existsById(String.valueOf(invoiceId)) ) {
            System.out.println("There is already an invoice with the same id");
        }
        Invoice invoice = new Invoice();
        invoice.setCreatedAt(createdAt); invoice.setPaymentDue(paymentDue);
        invoice.setPaymentTerms(paymentTerms); invoice.setDescription(description);
        invoice.setClientName(clientName); invoice.setClientEmail(clientEmail);
        invoice.setStatus(status); invoice.setSenderAddress(senderAddress);
        invoice.setClientAddress(clientAddress); invoice.setItems(items);
        invoice.setTotal(total);
        invoiceCrudRepository.save(invoice);
    }

    public void delete(String invoiceId) {
        invoiceCrudRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException(invoiceId));
        invoiceCrudRepository.deleteById(invoiceId);
    }
}
