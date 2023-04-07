package com.example.invoiceproject.service;

import com.example.invoiceproject.entity.GeneralUser;
import com.example.invoiceproject.entity.Invoice;
import com.example.invoiceproject.exception.ErrorTemplate;
import com.example.invoiceproject.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    /*private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;*/

    public ResponseEntity<Object> register(String name, String password) {
        if (userRepository.findGeneralUserByName(name) != null) {
            System.out.println("There is already a user with such a name.");
        }
        //GeneralUser user = new GeneralUser(name, passwordEncoder.encode(password));
        List<Invoice> invoiceList = new ArrayList<>();
        GeneralUser user = new GeneralUser(name, password, invoiceList);
        userRepository.save(user);
        return new ResponseEntity<>(new ErrorTemplate(String.format("User %d created", user.getId())),
                HttpStatus.OK);
    }

    /*public JwtResponse authenticateUser(String name1, String password1) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    name1, password1));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception(e.getMessage()+" "+e.getLocalizedMessage());
        }
        Person user = personRepository.getPersonByClientName(
                name1);
        if (user == null) {
            throw new UsernameNotFoundException(name1);
        }
        AccountUserDetails accountUserDetails = new AccountUserDetails(user);
        String jwtToken = jwtService.generateToken(accountUserDetails);
        return new JwtResponse(jwtToken);
    }*/
}
