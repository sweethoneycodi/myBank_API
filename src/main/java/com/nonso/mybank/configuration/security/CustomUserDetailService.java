package com.nonso.mybank.configuration.security;

import com.nonso.mybank.model.User;
import com.nonso.mybank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
@JsonComponent
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
            .orElseThrow(()->
                    new UsernameNotFoundException("user with the email " +email +" is not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
