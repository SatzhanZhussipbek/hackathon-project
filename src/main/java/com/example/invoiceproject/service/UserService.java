package com.example.invoiceproject.service;

import com.example.invoiceproject.entity.GeneralUser;
import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.exception.ErrorTemplate;
import com.example.invoiceproject.model.JwtResponse;
import com.example.invoiceproject.repository.UserCrudRepository;
import com.example.invoiceproject.security.GeneralUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.invoiceproject.security.JwtService;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserCrudRepository userCrudRepository;

    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;
    @Autowired
    public UserService(UserCrudRepository userCrudRepository, PasswordEncoder passwordEncoder,
                       JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userCrudRepository = userCrudRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<Object> register(String name, String password) {
        if (userCrudRepository.findGeneralUserByName(name) != null) {
            System.out.println("There is already a user with such a name.");
        }
        List<Invoice> invoiceList = new ArrayList<>();
        GeneralUser user = new GeneralUser(name, passwordEncoder.encode(password), invoiceList);
        userCrudRepository.save(user);
        return new ResponseEntity<>(new ErrorTemplate(String.format("User %d created", user.getId())),
                HttpStatus.OK);
    }

    public JwtResponse authenticateUser(String name1, String password1) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    name1, password1));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception(e.getMessage()+" "+e.getLocalizedMessage());
        }
        GeneralUser user = userCrudRepository.findGeneralUserByName(name1);
        if (user == null) {
            throw new UsernameNotFoundException(name1);
        }
        GeneralUserDetails generalUserDetails = new GeneralUserDetails(user);
        String jwtToken = jwtService.generateToken(generalUserDetails);
        return new JwtResponse(jwtToken);
    }
}
