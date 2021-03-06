package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.dto.DepartmentDto;
import com.btc.lecoorde.trainee_administration.model.dto.TraineeDto;
import com.btc.lecoorde.trainee_administration.service.DepartmentService;
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
//Test
@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Department getSingleDepartment(@PathVariable("id") Long id) {
//
//        logger.info("Anfrage: Abteilung mit der ID: " + id);
//
//        return departmentService.getDepartmentById(id);
//    }

//    @RequestMapping(value = "/trainee_list/{id}", method = RequestMethod.GET)
//    public List<TraineeDto> getTraineeListById(@PathVariable("id") Long id) {
//
//        logger.info("Anfrage: Liste von Auszubildenden für Abteilung mit ID: " + id);
//
//        return departmentService.getTraineeListForDepartmentId(id);
//    }

    @RequestMapping(value = "/createDepartment/", method = RequestMethod.POST)
    public ResponseEntity createDepartment(@RequestBody DepartmentDto input) {
        logger.info("Anfrage: Department speichern.");
        try {
            departmentService.createDepartment(input);
            logger.info("Department gespeichert: " + input);
            return new ResponseEntity(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteDepartment(@PathVariable Long id) {
        logger.info("Anfrage: Department löschen");
        try {
            departmentService.deleteDepartment(id);
            logger.info("Service: Abteilung mit ID: " + id + " wurde gelöscht!");
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DepartmentDto> getDepartmentList() {

        logger.info("Anfrage: Liste von Abteilungen");

        return departmentService.getAllDepartments();
    }


}

