package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.entity.InvoiceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceSortRepository extends PagingAndSortingRepository<Invoice, Long> {
        List<Invoice> findAllByStatus (InvoiceStatus status, Pageable pageable);
}
