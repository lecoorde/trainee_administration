package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.entity.Skill;
import com.btc.lecoorde.trainee_administration.model.skill.dto.SkillDTO;
import com.btc.lecoorde.trainee_administration.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Denis Simon on 17.02.2016.
 */
@RestController
@RequestMapping(value = "/skills")
public class SkillController {

    private final Logger logger = LoggerFactory.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SkillDTO> getSkillList() {

        logger.info("Anfrage: Liste von Skills");

        return skillService.getAllSkills();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Skill getSkill(@PathVariable("id") Long id) {

        logger.info("Anfrage: Skill mit der ID: " + id);

        return skillService.getSkillById(id);
    }
}

