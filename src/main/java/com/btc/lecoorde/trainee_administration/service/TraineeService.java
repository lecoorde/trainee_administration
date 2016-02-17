package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.Trainee;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis Simon on 16.02.2016.
 */
public class TraineeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TraineeDTO> getAllTrainees() {
        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Trainee t order by t.id", Trainee.class);
        List<Trainee> traineeList = query.getResultList();
        List<TraineeDTO> traineeDTOList = new LinkedList<>();
        for (Trainee t : traineeList) {
            traineeDTOList.add(new TraineeDTO(t.getId(),
                    t.getLastName(),
                    t.getForename(),
                    t.getJob(),
                    t.getBirthday(),
                    t.getStart_of_training()));
        }
        return traineeDTOList;
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