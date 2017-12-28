package com.thach.example.service;

import com.thach.example.dao.UserDAO;
import com.thach.example.model.CalculationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by THACH-PC
 */

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDAO userRepository;

    public CalculationUser findUser(String username){
        return userRepository.find(username);
    }

    public void createUser(CalculationUser calculationUser){
        userRepository.create(calculationUser);
    }
}
