package com.thach.example.dao;

import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by THACH-PC on 12/27/2017.
 */

@Repository
public class HistoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<History> getHistory(String username){
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        builder.
//        Criteria criteria = builder.createQuery(History.class);
        String query = "from History";
        List result = entityManager.createQuery(query).getResultList();

        return result;

//        return entityManager.
    }

    public void create(History history){
        entityManager.persist(history);
    }
}
