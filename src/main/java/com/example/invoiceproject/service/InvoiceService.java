package com.example.invoiceproject.service;

import com.example.invoiceproject.entity.*;
import com.example.invoiceproject.exception.InvoiceNotFoundException;
import com.example.invoiceproject.repository.InvoiceCrudRepository;
import com.example.invoiceproject.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceService {
    @Autowired
    InvoiceCrudRepository invoiceCrudRepository;

    @Autowired
    UserCrudRepository userCrudRepository;

    public Invoice getInvoice (String invoiceId) {
        if (invoiceCrudRepository.getInvoiceById(invoiceId) != null) {
            return invoiceCrudRepository.getInvoiceById(invoiceId);
        }
        throw new InvoiceNotFoundException(invoiceId);
    }

    public void updateStatus(String status, String id, long userId) {
        invoiceCrudRepository.updateInvoiceStatus(status, id);
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        for (int i = 0; i < user.getInvoices().size(); i++) {
            if (Objects.equals(user.getInvoices().get(i).getId(), id)) {
                user.getInvoices().get(i).setStatus(status);
            }
        }
        userCrudRepository.save(user);
    }

    public void create(String id, String createdAt, String paymentDue, int paymentTerms, String description,
                       String clientName, String clientEmail, String status,
                       Address senderAddress, ClientAddress clientAddress,
                       List<InvoiceItem> items, double total, long userId) {
        if (invoiceCrudRepository.findById(id).isPresent()) {
            System.out.println("There is already an invoice with the same id");
        }
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        Invoice invoice = new Invoice();
        invoice.setCreatedAt(createdAt); invoice.setPaymentDue(paymentDue);
        invoice.setPaymentTerms(paymentTerms); invoice.setDescription(description);
        invoice.setClientName(clientName); invoice.setClientEmail(clientEmail);
        invoice.setStatus(status); invoice.setSenderAddress(senderAddress);
        invoice.setClientAddress(clientAddress); invoice.setItems(items);
        invoice.setTotal(total);
        List<Invoice> newList = user.getInvoices();
        newList.add(invoice);
        user.setInvoices(newList);
        userCrudRepository.save(user);
        invoiceCrudRepository.save(invoice);
    }

    public void delete(String invoiceId, long userId) {
        invoiceCrudRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException(invoiceId));
        invoiceCrudRepository.deleteById(invoiceId);
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        Invoice invoice = invoiceCrudRepository.getInvoiceById(invoiceId);
        user.getInvoices().remove(invoice);
        userCrudRepository.save(user);

    }
}
