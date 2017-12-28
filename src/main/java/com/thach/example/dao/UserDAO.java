package com.thach.example.dao;

import com.thach.example.model.CalculationUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by THACH-PC on 12/27/2017.
 */

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public CalculationUser getUser(String username){
        CalculationUser calculationUser = entityManager.find(CalculationUser.class, username);
        return calculationUser;
    }

    public void save(CalculationUser calculationUser){
        entityManager.persist(calculationUser);
    }
}
