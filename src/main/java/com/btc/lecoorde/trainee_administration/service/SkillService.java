package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.dto.TraineeDto;
import com.btc.lecoorde.trainee_administration.model.entity.Skill;
import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.dto.SkillDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class SkillService {

    private final Logger logger = LoggerFactory.getLogger(SkillService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Skill getSkillById(Long id) {

        logger.info("Service lädt den Skill");

        TypedQuery<Skill> query = this.entityManager.createQuery("select s from Skill s " +
                "where s.id = " + id, Skill.class);
        return query.getSingleResult();
    }

    public List<TraineeDto> getTraineeListForSkillId(Long id) {
        logger.info("Service lädt die Liste von Auszubildenden für Skill-ID " + id);

        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Skill s " +
                "join s.trainees t " +
                "where s.id = " + id, Trainee.class);

        List<Trainee> traineeList = query.getResultList();


        return traineeList
                .stream()
                .map(t -> new TraineeDto(t.getId(), t.getLastName(), t.getForename(), null, null, null, null, null))
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @Transactional
    public void deleteSkill(Long id) {
        this.entityManager.remove(this.entityManager.find(Skill.class, id));
    }

    @Transactional
    public void createSkill(SkillDto skillDto) {
        Skill skill = new Skill();
        skill.setName(skillDto.getName());
        skill.setDescription(skillDto.getDescription());
        skill.setTrainees(new HashSet<>());
        entityManager.persist(skill);
    }

    public List<SkillDto> getAllSkills() {

        logger.info("Service lädt die Liste von Skills");

        TypedQuery<Skill> query = this.entityManager.createQuery("select s from Skill s " +
                "order by s.id", Skill.class);
        List<Skill> skillList = query.getResultList();
        return skillList
                .stream()
                .map(s -> new SkillDto(
                        s.getId(),
                        s.getName(),
                        s.getDescription()))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
