package com.thach.example.service;

import com.thach.example.dao.UserDAO;
import com.thach.example.error.EnumError;
import com.thach.example.model.CalculationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by THACH-PC
 */

@Transactional
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userRepository;

    public CalculationUser findUser(String username){
        return userRepository.find(username);
    }

    public void createUser(CalculationUser calculationUser){
        userRepository.create(calculationUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CalculationUser user = this.findUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(EnumError.USERNAME_NOT_FOUND.getDescription());
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), Arrays.asList(authority));

        return userDetails;
    }
}
