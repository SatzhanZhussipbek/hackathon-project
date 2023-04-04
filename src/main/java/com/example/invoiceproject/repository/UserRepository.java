package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.GeneralUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<GeneralUser, Long> {

}
