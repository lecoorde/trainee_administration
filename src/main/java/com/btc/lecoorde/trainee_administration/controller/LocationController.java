package com.btc.lecoorde.trainee_administration.controller;

import com.btc.lecoorde.trainee_administration.model.location.dto.LocationDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
import com.btc.lecoorde.trainee_administration.service.LocationService;
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
@RequestMapping(value = "/locations")
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/trainee_list/{id}", method = RequestMethod.GET)
    public List<TraineeDTO> getTraineeListById(@PathVariable("id") Long id) {

        logger.info("Anfrage: Liste von Auszubildenden für Standort mit ID: " + id);

        return locationService.getTraineeListForSkillId(id);
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Location getLocation(@PathVariable("id") Long id) {
//
//        logger.info("Anfrage: Standort mit der ID: " + id);
//
//        return locationService.getLocationById(id);
//    }

    @RequestMapping(value = "/createLocation/", method = RequestMethod.POST)
    public ResponseEntity createDepartment(@RequestBody LocationDTO input) {
        logger.info("Anfrage: Location speichern.");
        try {
            locationService.createLocation(input);
            logger.info("Location gespeichert: " + input);
            return new ResponseEntity(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteLocation(@PathVariable Long id) {
        logger.info("Anfrage: Standort löschen");
        try {
            locationService.deleteLocation(id);
            logger.info("Service: Standort mit ID: " + id + " wurde gelöscht!");
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<LocationDTO> getLocationList() {

        logger.info("Anfrage: Liste von Standorten");

        return locationService.getAllLocations();
    }
}
