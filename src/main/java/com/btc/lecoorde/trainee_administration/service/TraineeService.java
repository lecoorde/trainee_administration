package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.entity.Skill;
import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.skill.dto.SkillDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDetailDTO;
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
                    t.getStart_of_training()));
        }
        return traineeDTOList;
    }

    public TraineeDetailDTO getTraineeById(Long id) {

        logger.info("Service lädt den Auszubildenden");

        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Trainee t " +
                "where t.id = " + id, Trainee.class);
        Trainee trainee = query.getSingleResult();

        TraineeDetailDTO traineeDetailDTO;

        traineeDetailDTO = new TraineeDetailDTO(trainee.getId(),
                trainee.getLastName(),
                trainee.getForename(),
                trainee.getJobName(),
                trainee.getBirthday(),
                trainee.getStart_of_training(),
                trainee.getDepartment().getName(),
                trainee.getLocation().getName());

        return traineeDetailDTO;

    }

    public List<SkillDTO> getSkillListByTraineeId(Long id) {

        logger.info("Service lädt die Liste von Skills");

        TypedQuery<Skill> query = this.entityManager.createQuery("select s from Trainee t " +
                "join t.skillList s " +
                "where t.id = " + id, Skill.class);

        List<Skill> skillList = query.getResultList();

        List<SkillDTO> skillDTOList = new LinkedList<>();

        for (Skill s : skillList) {
            skillDTOList.add(new SkillDTO(s.getId(), s.getName(), s.getDescription()));
        }
        return skillDTOList;
    }
}