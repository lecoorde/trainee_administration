package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.dto.TraineeDto;
import com.btc.lecoorde.trainee_administration.model.entity.Location;
import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.dto.LocationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class LocationService {

    private final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setName(locationDto.getName());
        location.setStreet(locationDto.getStreet());
        location.setCity(locationDto.getCity());
        location.setHouseNum(locationDto.getHouseNum());
        location.setPostCode(locationDto.getPostCode());
        entityManager.persist(location);
    }

    public Location getLocationById(Long id) {

        logger.info("Service lädt den Standort");

        TypedQuery<Location> query = this.entityManager.createQuery("select l from Location l " +
                "where l.id = " + id, Location.class);
        return query.getSingleResult();
    }

//    public List<TraineeDto> getTraineeListForSkillId(Long id) {
//
//        logger.info("Service lädt die Liste von Auszubildenden für Standort-ID " + id);
//
//        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Location l " +
//                "join l.trainees t " +
//                "where l.id = " + id, Trainee.class);
//
//        List<Trainee> traineeList = query.getResultList();
//
//        return traineeList
//                .stream()
//                .map(t -> new TraineeDto(t.getId(), t.getLastName(), t.getForename(), null, null, null, null, null))
//                .collect(Collectors.toCollection(LinkedList::new));
//
//    }

    @Transactional
    public void deleteLocation(Long id) {
        this.entityManager.remove(this.entityManager.find(Location.class, id));
    }

    public List<LocationDto> getAllLocations() {

        logger.info("Service lädt die Liste von Standorten");

        TypedQuery<Location> query = this.entityManager.createQuery("select l from Location l " +
                "order by l.id", Location.class);
        List<Location> locationList = query.getResultList();
        return locationList
                .stream()
                .map(l -> new LocationDto(
                        l.getId(),
                        l.getName(),
                        l.getStreet(),
                        l.getHouseNum(),
                        l.getPostCode(),
                        l.getCity()))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
