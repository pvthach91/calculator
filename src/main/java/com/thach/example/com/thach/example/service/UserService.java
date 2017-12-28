package com.thach.example.com.thach.example.service;

import com.thach.example.dao.UserDAO;
import com.thach.example.model.CalculationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by THACH-PC on 12/27/2017.
 */

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDAO userRepository;

    public CalculationUser getUser(String username){
        CalculationUser calculationUser = userRepository.getUser(username);
        return calculationUser;
    }

    public void createUser(CalculationUser calculationUser){
        userRepository.save(calculationUser);
    }
}
