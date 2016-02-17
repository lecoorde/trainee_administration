package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.Trainee;
import com.btc.lecoorde.trainee_administration.service.TraineeService;
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
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Trainee> findTrainees() {

        return traineeService.getAllTrainees();
    }

    @RequestMapping("/trainee/{id}")
    public Trainee getTrainee(@PathVariable("id") Long id) {
        System.out.println("Auszubildender mit der ID: "+id+" wird gesucht...");
        return traineeService.getTraineeById(id);
    }

}
