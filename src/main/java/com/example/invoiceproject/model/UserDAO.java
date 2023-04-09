package com.example.invoiceproject.model;

import com.example.invoiceproject.entity.GeneralUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDAO {

    private long id;

    private String username;

    public UserDAO(GeneralUser person) {
        this.id = person.getId();
        this.username = person.getName();
    }
}
