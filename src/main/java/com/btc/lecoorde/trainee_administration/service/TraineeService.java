package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis Simon on 16.02.2016.
 */
public class TraineeService {

    private final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<TraineeDTO> getAllTrainees() {

        logger.info("Service lädt die Liste von Auzubildenden");

        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Trainee t " +
                "order by t.id", Trainee.class);
        List<Trainee> traineeList = query.getResultList();
        List<TraineeDTO> traineeDTOList = new LinkedList<>();
        for (Trainee t : traineeList) {
            traineeDTOList.add(new TraineeDTO(t.getId(),
                    t.getLastName(),
                    t.getForename(),
                    t.getJobName(),
                    t.getBirthday(),
                    t.getStart_of_training(),
                    t.getDepartment().getName()));
        }
        return traineeDTOList;
    }

    public Trainee getTraineeById(Long id) {

        logger.info("Service lädt den Auszubildenden");

        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Trainee t " +
                "where t.id = " + id, Trainee.class);
        return query.getSingleResult();

    }
}