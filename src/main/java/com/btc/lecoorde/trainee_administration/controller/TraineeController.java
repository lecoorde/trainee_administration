package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.skill.dto.SkillDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.CreateTraineeDto;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
import com.btc.lecoorde.trainee_administration.service.TraineeService;
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
    public ResponseEntity<CreateTraineeDto> createUser(@RequestBody CreateTraineeDto input) {
        logger.info("Anfrage: Trainee Speichern.");
        try {
            traineeService.createTrainee(input);
            logger.info("Trainee gespeichert: " + input);
            return new ResponseEntity<CreateTraineeDto>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CreateTraineeDto>(HttpStatus.CONFLICT);
        }

    }
}
