package com.example.invoiceproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="General_user")
public class GeneralUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    public GeneralUser(String name, String password, List<Invoice> invoices) {
        this.name = name;
        this.password = password;
        this.invoices = invoices;
    }

}
