package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.entity.Skill;
import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.skill.dto.SkillDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class SkillService {

    private final Logger logger = LoggerFactory.getLogger(SkillService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<SkillDTO> getAllSkills() {

        logger.info("Service lädt die Liste von Skills");

        TypedQuery<Skill> query = this.entityManager.createQuery("select s from Skill s " +
                "order by s.id", Skill.class);
        List<Skill> skillList = query.getResultList();
        List<SkillDTO> skillDTOList = new LinkedList<>();
        for (Skill s : skillList) {
            skillDTOList.add(new SkillDTO(
                    s.getId(),
                    s.getName(),
                    s.getDescription()));
        }
        return skillDTOList;
    }

    public Skill getSkillById(Long id) {

        logger.info("Service lädt den Skill");

        TypedQuery<Skill> query = this.entityManager.createQuery("select s from Skill s " +
                "where s.id = " + id, Skill.class);
        return query.getSingleResult();
    }

    public List<TraineeDTO> getTraineeListForSkillId(Long id) {
        logger.info("Service lädt die Liste von Auszubildenden für Skill-ID "+id);

        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Skill s " +
                "join s.trainees t " +
                "where s.id = " + id, Trainee.class);

        List<Trainee> traineeList = query.getResultList();

        List<TraineeDTO> traineeDTOList = new LinkedList<>();

        for (Trainee t : traineeList) {
            traineeDTOList.add(new TraineeDTO(t.getId(), t.getLastName(), t.getForename(), null, null, null, null, null));
        }
        return traineeDTOList;

    }
}
