package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.entity.Skill;
import com.btc.lecoorde.trainee_administration.model.skill.dto.SkillDTO;
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

    public Skill getSkillById(Long id) {

        logger.info("Service lädt den Skill");

        TypedQuery<Skill> query = this.entityManager.createQuery("select s from Skill s " +
                "where s.id = " + id, Skill.class); //TODO Detailansicht
        return query.getSingleResult();
    }

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
}
