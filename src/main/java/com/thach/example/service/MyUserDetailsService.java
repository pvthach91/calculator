package com.thach.example.service;

import com.thach.example.dao.UserDAO;
import com.thach.example.model.CalculationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Thach on 1/3/2018.
 */

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDAO userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        CalculationUser user = userRepository.find(username);
        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), Arrays.asList(authority));


         return userDetails;
    }
}
