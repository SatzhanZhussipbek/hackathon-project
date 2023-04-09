package com.example.invoiceproject.controller;

import com.example.invoiceproject.entity.GeneralUser;
import com.example.invoiceproject.entity.InvoiceStatus;
import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.exception.ErrorTemplate;
import com.example.invoiceproject.repository.InvoiceCrudRepository;
import com.example.invoiceproject.repository.InvoiceDetails;
import com.example.invoiceproject.repository.UserCrudRepository;
import com.example.invoiceproject.service.InvoiceService;
import com.example.invoiceproject.service.InvoiceSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceSortService invoiceSortService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    UserCrudRepository userCrudRepository;
    @Autowired
    InvoiceCrudRepository invoiceCrudRepository;
    @GetMapping("/invoices")
    public List<InvoiceDetails> getInvoices (@RequestParam String status, @RequestParam long userId, Pageable pageable,
                                             Authentication authentication) {
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        if (!authentication.getPrincipal().equals(user.getName()) ||
                !authentication.getCredentials().equals(user.getPassword()) )  {
            throw new AccessDeniedException("Access denied. You entered the wrong id. ");
        }
        List<InvoiceDetails> InvoicesList = invoiceSortService.filterInvoices(userId, status, pageable);
        return InvoicesList;
    }
    @GetMapping("/invoices/{id}")
    public Invoice getInvoice (@PathVariable String id, @RequestParam long userId, Authentication authentication) {
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        if (!authentication.getPrincipal().equals(user.getName()) ||
                !authentication.getCredentials().equals(user.getPassword()) )  {
            throw new AccessDeniedException("Access denied. You entered the wrong id. ");
        }
        Invoice invoice = invoiceService.getInvoice(id);
        return invoice;
    }

    @PostMapping("/invoices/create")
    public ResponseEntity<Object> createInvoice(@RequestBody Invoice invoice, @RequestParam
                                                long userId, Authentication authentication) {
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        if (!authentication.getPrincipal().equals(user.getName()) ||
                !authentication.getCredentials().equals(user.getPassword()) )  {
            throw new AccessDeniedException("Access denied. You entered the wrong id. ");
        }
        invoiceService.create(invoice.getId(), invoice.getCreatedAt(), invoice.getPaymentDue(),
                invoice.getPaymentTerms(), invoice.getDescription(), invoice.getClientName(),
                invoice.getClientEmail(), invoice.getStatus(), invoice.getSenderAddress(),
                invoice.getClientAddress(), invoice.getItems(), invoice.getTotal(), userId);
        return new ResponseEntity<>(new ErrorTemplate(("Invoice was created")),
                HttpStatus.CREATED);
    }
    @PostMapping("/invoices/{id}/update")
    public ResponseEntity<Object> updateInvoice(@PathVariable
                                                String id, @RequestParam String status,
                                                @RequestParam long userId,
                                                Authentication authentication) {
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        if (!authentication.getPrincipal().equals(user.getName()) ||
                !authentication.getCredentials().equals(user.getPassword()) )  {
            throw new AccessDeniedException("Access denied. You entered the wrong id. ");
        }
        invoiceService.updateStatus(status, id, userId);
        Invoice invoice = invoiceCrudRepository.getInvoiceById(id);
        return new ResponseEntity<>(new ErrorTemplate(String.format("Account %s updated", invoice.getId()) ),
                HttpStatus.OK);
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable String id, @RequestParam long userId,
                                                Authentication authentication) {
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        if (!authentication.getPrincipal().equals(user.getName()) ||
                !authentication.getCredentials().equals(user.getPassword()) )  {
            throw new AccessDeniedException("Access denied. You entered the wrong id. ");
        }
        invoiceService.delete(id, userId);
        return new ResponseEntity<>(new ErrorTemplate(String.format("Account %s deleted", id) ),
                HttpStatus.OK);
    }
}
