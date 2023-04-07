package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.GeneralUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<GeneralUser, Long> {

    GeneralUser findGeneralUserById(long userId);

    GeneralUser findGeneralUserByName(String name);

}
