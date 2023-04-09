package com.example.invoiceproject.security;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.example.invoiceproject.entity.GeneralUser;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
@NoArgsConstructor

public class GeneralUserDetails extends GeneralUser implements UserDetails {

    private String username;
    private String password;

    private static final List<GrantedAuthority> ROLE_USER = Collections
            .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));

    public GeneralUserDetails(GeneralUser person) {
        username = person.getName();
        password = person.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ROLE_USER;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

