package com.thach.example.dao;

import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public class HistoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<History> getHistoriesByUser(CalculationUser user){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<History> criteria = builder.createQuery(History.class);
        Root<History> history = criteria.from(History.class);
        criteria.select(history);
        criteria.where(builder.equal(history.get("createdBy"), user));
        criteria.orderBy(builder.desc(history.get("date")));
        List<History> histories = entityManager.createQuery(criteria).getResultList();

        return histories;
    }

    public void createHistory(History history){
        entityManager.persist(history);
    }
}
