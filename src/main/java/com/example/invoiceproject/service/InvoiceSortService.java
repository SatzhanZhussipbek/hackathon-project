package com.example.invoiceproject.service;

import com.example.invoiceproject.entity.GeneralUser;
import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.entity.InvoiceStatus;
import com.example.invoiceproject.repository.InvoiceDetails;
import com.example.invoiceproject.repository.InvoiceSortRepository;
import com.example.invoiceproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceSortService {
    @Autowired
    InvoiceSortRepository invoiceSortRepository;

    @Autowired
    UserRepository userRepository;

    public List<InvoiceDetails> filterInvoices (Long userId, InvoiceStatus status, Pageable pageable) {
        List<Invoice> invoices = invoiceSortRepository.findAllByStatus(status, pageable);
        GeneralUser user = userRepository.findGeneralUserById(userId);
        List<InvoiceDetails> list = new ArrayList<>();
        for (Invoice i: invoices) {
            for (Invoice inv: user.getInvoices()) {
                if (Objects.equals(i.getId(), inv.getId())) {
                    InvoiceDetails invoiceDetails = new InvoiceDetails();
                    invoiceDetails.setId(i.getId());
                    invoiceDetails.setStatus(InvoiceStatus.valueOf(i.getPaymentDue()));
                    invoiceDetails.setClientName(i.getClientName());
                    invoiceDetails.setPrice(i.getTotal());
                    invoiceDetails.setStatus(i.getStatus());
                    list.add(invoiceDetails);
                }
            }
        }
        return list;
    }
}
