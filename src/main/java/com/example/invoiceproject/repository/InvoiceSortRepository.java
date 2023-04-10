package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.entity.InvoiceDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceSortRepository extends PagingAndSortingRepository<Invoice, Long> {
        @Query(value =
                "SELECT new com.example.invoiceproject.entity.InvoiceDetails" +
                        "(i.id, i.paymentDue, i.clientName, i.total, i.status) " +
                "from Invoice i where i.status=:status")
        List<InvoiceDetails> findAllByStatus (String status, Pageable pageable);
}
