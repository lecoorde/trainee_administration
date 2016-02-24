package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.dto.SkillDto;
import com.btc.lecoorde.trainee_administration.model.dto.TraineeDto;
import com.btc.lecoorde.trainee_administration.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/trainee_list/{id}", method = RequestMethod.GET)
    public List<TraineeDto> getTraineeListById(@PathVariable("id") Long id) {

        logger.info("Anfrage: Liste von Auszubildenden für Skill mit ID: " + id);

        return skillService.getTraineeListForSkillId(id);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Skill getSkill(@PathVariable("id") Long id) {
//
//        logger.info("Anfrage: Skill mit der ID: " + id);
//
//        return skillService.getSkillById(id);
//    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        logger.info("Anfrage: Skill löschen");
        try {
            skillService.deleteSkill(id);
            logger.info("Service: Skill mit ID: " + id + " wurde gelöscht!");
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/createSkill/", method = RequestMethod.POST)
    public ResponseEntity createSkill(@RequestBody SkillDto input) {
        logger.info("Anfrage: Skill speichern.");
        try {
            skillService.createSkill(input);
            logger.info("Skill gespeichert: " + input);
            return new ResponseEntity(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SkillDto> getSkillList() {

        logger.info("Anfrage: Liste von Skills");

        return skillService.getAllSkills();
    }
}

