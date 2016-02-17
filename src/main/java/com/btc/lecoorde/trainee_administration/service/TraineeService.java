package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.Trainee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Denis Simon on 16.02.2016.
 */
public class TraineeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Trainee> getAllTrainees() {
        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Trainee t ", Trainee.class);
        List<Trainee> trainees = query.getResultList();
        return trainees;
    }

    public Trainee getTraineeById(Long id) {
        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Trainee t " +
                "join fetch t.department d " +
                "join fetch t.location l " +
                "join fetch t.skillList s " +
                "where t.id = " + id, Trainee.class);
        return query.getSingleResult();
    }
}