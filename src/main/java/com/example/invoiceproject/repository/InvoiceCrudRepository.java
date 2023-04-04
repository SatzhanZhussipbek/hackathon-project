package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

interface InvoiceCrudRepository extends CrudRepository<Invoice, Long> {
}
