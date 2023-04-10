package com.example.invoiceproject.service;

import com.example.invoiceproject.entity.GeneralUser;
import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.entity.InvoiceDetails;
import com.example.invoiceproject.repository.InvoiceSortRepository;
import com.example.invoiceproject.repository.UserCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class InvoiceSortService {
    @Autowired
    InvoiceSortRepository invoiceSortRepository;

    @Autowired
    UserCrudRepository userCrudRepository;

    public List<InvoiceDetails> filterInvoices (Long userId, String status, Pageable pageable) {
        List<InvoiceDetails> invoices = invoiceSortRepository.findAllByStatus(status, pageable);
        GeneralUser user = userCrudRepository.findGeneralUserById(userId);
        List<InvoiceDetails> list = new ArrayList<>();
        for (InvoiceDetails i: invoices) {
            for (Invoice inv: user.getInvoices()) {
                if (Objects.equals(i.getId(), inv.getId())) {
                    InvoiceDetails invoiceDetails = new InvoiceDetails();
                    invoiceDetails.setId(i.getId());
                    invoiceDetails.setDueDate(i.getDueDate());
                    invoiceDetails.setClientName(i.getClientName());
                    invoiceDetails.setPrice(i.getPrice());
                    invoiceDetails.setStatus(i.getStatus());
                    list.add(invoiceDetails);
                }
            }
        }
        return list;
    }
}
