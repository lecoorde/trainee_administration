package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.entity.Department;
import com.btc.lecoorde.trainee_administration.model.department.dto.DepartmentDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
import com.btc.lecoorde.trainee_administration.service.DepartmentService;
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
@RequestMapping(value = "/departments")
public class DepartmentController {

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DepartmentDTO> getDepartmentList() {

        logger.info("Anfrage: Liste von Abteilungen");

        return departmentService.getAllDepartments();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Department getSingleDepartment(@PathVariable("id") Long id) {

        logger.info("Anfrage: Abteilung mit der ID: " + id);

        return departmentService.getDepartmentById(id);
    }

    @RequestMapping(value = "/trainee_list/{id}", method = RequestMethod.GET)
    public List<TraineeDTO> getTraineeListById(@PathVariable("id") Long id) {

        logger.info("Anfrage: Liste von Auszubildenden für Abteilung mit ID: " + id);

        return departmentService.getTraineeListForDepartmentId(id);
    }
}

