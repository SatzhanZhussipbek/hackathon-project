package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceSortRepository extends PagingAndSortingRepository<Invoice, Long> {

}
