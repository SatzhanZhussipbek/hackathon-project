package com.example.invoiceproject.repository;

import com.example.invoiceproject.entity.GeneralUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepository extends CrudRepository<GeneralUser, Long> {

    @Query(value = "select u from GeneralUser u where u.id = ?1")
    GeneralUser findGeneralUserById(long userId);
    @Query(value = "select u from GeneralUser u where u.name = ?1")
    GeneralUser findGeneralUserByName(String name);

}
