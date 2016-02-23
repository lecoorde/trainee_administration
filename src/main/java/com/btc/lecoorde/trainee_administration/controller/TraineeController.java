package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.skill.dto.SkillDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.CreateTraineeDto;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
import com.btc.lecoorde.trainee_administration.service.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Denis Simon on 17.02.2016.
 */
@RestController
@RequestMapping(value = "/trainees")
public class TraineeController {

    private final Logger logger = LoggerFactory.getLogger(TraineeController.class);

    @Autowired
    private TraineeService traineeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TraineeDTO> getTraineeList() {

        logger.info("Anfrage: Liste von Auszubildenden");

        return traineeService.getAllTrainees();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public TraineeDTO getSingleTrainee(@PathVariable("id") Long id) {
//
//        logger.info("Anfrage: Auszubildender mit der ID: " + id);
//
//        return traineeService.getTraineeById(id);
//    }

    @RequestMapping(value = "/skill_list/{id}", method = RequestMethod.GET)
    public List<SkillDTO> getSkillListById(@PathVariable("id") Long id) {

        logger.info("Anfrage: Liste von Skills für Auszubildenden mit ID: " + id);

        return traineeService.getSkillListByTraineeId(id);
    }

    @RequestMapping(value = "/createTrainee/", method = RequestMethod.POST)
    public void createUser(@RequestBody CreateTraineeDto input) {
        logger.info("Anfrage: Auszubildenden speichern.");
        traineeService.createTrainee(input);
        logger.info("Service hat den Auszubildenden gespeichert: " + input);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void deleteTrainee(@PathVariable Long id) {
        logger.info("Anfrage: Auszubildenden löschen mit der ID: "+id);
        traineeService.deleteTrainee(id);
        logger.info("Service hat den Auszubildenden mit der ID: "+id+" gelöscht." );
    }
}
