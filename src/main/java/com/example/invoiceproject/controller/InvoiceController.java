package com.example.invoiceproject.controller;

import com.example.invoiceproject.entity.GeneralUser;
import com.example.invoiceproject.entity.InvoiceStatus;
import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.exception.ErrorTemplate;
import com.example.invoiceproject.repository.InvoiceCrudRepository;
import com.example.invoiceproject.repository.InvoiceDetails;
import com.example.invoiceproject.repository.UserRepository;
import com.example.invoiceproject.service.InvoiceService;
import com.example.invoiceproject.service.InvoiceSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceSortService invoiceSortService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvoiceCrudRepository invoiceCrudRepository;
    @GetMapping("/invoices")
    public List<InvoiceDetails> getInvoices (@RequestParam String status, @RequestParam long userId, Pageable pageable) {
        List<InvoiceDetails> InvoicesList = invoiceSortService.filterInvoices(userId, InvoiceStatus.valueOf(status), pageable);
        return InvoicesList;
    }
    @GetMapping("/invoices/{id}")
    public Invoice getInvoice (@RequestParam String id, @RequestParam long userId) {
        Invoice invoice = invoiceService.getInvoice(id);
        return invoice;
    }

    @PostMapping("/invoices/create")
    public ResponseEntity<Object> createInvoice(@RequestBody Invoice invoice) {
        invoiceService.create(invoice.getInvoiceId(), invoice.getCreatedAt(), invoice.getPaymentDue(),
                invoice.getPaymentTerms(), invoice.getDescription(), invoice.getClientName(),
                invoice.getClientEmail(), invoice.getStatus(), invoice.getSenderAddress(),
                invoice.getClientAddress(), invoice.getItems(), invoice.getTotal());
        return new ResponseEntity<>(new ErrorTemplate(String.format("Invoice %s created",
                invoice.getInvoiceId()) ),
                HttpStatus.CREATED);
    }
    @PostMapping("/invoices/{id}/update")
    public ResponseEntity<Object> updateInvoice(@RequestParam InvoiceStatus status, @RequestParam
                                                long invoiceId) {
        invoiceService.updateStatus(status, invoiceId);
        Invoice invoice = invoiceCrudRepository.getInvoiceByInvoiceId(invoiceId);
        return new ResponseEntity<>(new ErrorTemplate(String.format("Account %s deleted", invoice.getId()) ),
                HttpStatus.OK);
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable String id, @RequestParam long userId) {
        //GeneralUser user = userRepository.findGeneralUserById(userId);
        invoiceService.delete(id);
        return new ResponseEntity<>(new ErrorTemplate(String.format("Account %d deleted", id) ),
                HttpStatus.OK);
    }
}
