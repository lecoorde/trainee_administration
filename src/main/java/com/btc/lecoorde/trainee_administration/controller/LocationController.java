package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.entity.Location;
import com.btc.lecoorde.trainee_administration.model.location.dto.LocationDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
import com.btc.lecoorde.trainee_administration.service.LocationService;
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
@RequestMapping(value = "/locations")
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<LocationDTO> getLocationList() {

        logger.info("Anfrage: Liste von Standorten");

        return locationService.getAllLocations();
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Location getLocation(@PathVariable("id") Long id) {
//
//        logger.info("Anfrage: Standort mit der ID: " + id);
//
//        return locationService.getLocationById(id);
//    }

    @RequestMapping(value = "/trainee_list/{id}", method = RequestMethod.GET)
    public List<TraineeDTO> getTraineeListById(@PathVariable("id") Long id) {

        logger.info("Anfrage: Liste von Auszubildenden für Standort mit ID: " + id);

        return locationService.getTraineeListForSkillId(id);
    }
}
