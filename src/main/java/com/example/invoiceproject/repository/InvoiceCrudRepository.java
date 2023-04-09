package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.entity.InvoiceStatus;
import jakarta.transaction.Transactional;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceCrudRepository extends CrudRepository<Invoice, String> {
    @Transactional
    @Modifying
    @Query(value = "update Invoice i set i.status=?1 where i.id=?2")
    void updateInvoiceStatus(String status, String Id);

    @Query(value = "select i from Invoice i where i.id=?1")
    Invoice getInvoiceById(String id);


}
