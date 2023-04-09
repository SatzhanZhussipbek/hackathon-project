package com.example.invoiceproject.security;
import com.example.invoiceproject.entity.GeneralUser;
import com.example.invoiceproject.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class GeneralUserDetailsService implements UserDetailsService {

        private UserCrudRepository userCrudRepository;
        @Autowired
        public GeneralUserDetailsService(UserCrudRepository userCrudRepository) {
            this.userCrudRepository = userCrudRepository;
        }
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            GeneralUser person = userCrudRepository.findGeneralUserByName(username);
            if (person == null) {
                throw new UsernameNotFoundException("username "+username+" is not found");
            }
            return new GeneralUserDetails(person);
        }
}

