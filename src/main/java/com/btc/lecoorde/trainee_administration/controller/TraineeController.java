package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.dto.SkillDto;
import com.btc.lecoorde.trainee_administration.model.dto.CreateTraineeDto;
import com.btc.lecoorde.trainee_administration.model.dto.TraineeDto;
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

    @RequestMapping(value = "/skill_list/{id}", method = RequestMethod.GET)
    public List<SkillDto> getSkillListById(@PathVariable("id") Long id) {

        logger.info("Anfrage: Liste von Skills für Auszubildenden mit ID: " + id);

        return traineeService.getSkillListByTraineeId(id);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public TraineeDto getSingleTrainee(@PathVariable("id") Long id) {
//
//        logger.info("Anfrage: Auszubildender mit der ID: " + id);
//
//        return traineeService.getTraineeById(id);
//    }

    @RequestMapping(value = "/createTrainee/", method = RequestMethod.POST)
    public ResponseEntity<CreateTraineeDto> createTrainee(@RequestBody CreateTraineeDto input) {
        logger.info("Anfrage: Auszubildenden speichern");
        try {
            traineeService.createTrainee(input);
            logger.info("Service: Auszubildender gespeichert: " + input);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteTrainee(@PathVariable Long id) {
        logger.info("Anfrage: Auszubildenden löschen");
        try {
            traineeService.deleteTrainee(id);
            logger.info("Service: Auszubildender mit ID: " + id + " wurde gelöscht!");
            return new ResponseEntity(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TraineeDto> getTraineeList() {

        logger.info("Anfrage: Liste von Auszubildenden");

        return traineeService.getAllTrainees();
    }

}
