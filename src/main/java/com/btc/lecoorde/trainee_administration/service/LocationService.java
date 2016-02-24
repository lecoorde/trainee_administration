package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.entity.Location;
import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.location.dto.LocationDto;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class LocationService {

    private final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<LocationDto> getAllLocations() {

        logger.info("Service lädt die Liste von Standorten");

        TypedQuery<Location> query = this.entityManager.createQuery("select l from Location l " +
                "order by l.id", Location.class);
        List<Location> locationList = query.getResultList();
        List<LocationDto> locationDTOList = new LinkedList<>();
        for (Location l : locationList) {
            locationDTOList.add(new LocationDto(
                    l.getId(),
                    l.getName(),
                    l.getStreet(),
                    l.getHouseNum(),
                    l.getPostCode(),
                    l.getCity()));
        }
        return locationDTOList;
    }

    public Location getLocationById(Long id) {

        logger.info("Service lädt den Standort");

        TypedQuery<Location> query = this.entityManager.createQuery("select l from Location l " +
                "where l.id = " + id, Location.class);
        return query.getSingleResult();
    }

    public List<TraineeDto> getTraineeListForSkillId(Long id) {

        logger.info("Service lädt die Liste von Auszubildenden für Standort-ID "+id);

        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Location l " +
                "join l.trainees t " +
                "where l.id = " + id, Trainee.class);

        List<Trainee> traineeList = query.getResultList();

        List<TraineeDto> traineeDTOList = new LinkedList<>();

        for (Trainee t : traineeList) {
            traineeDTOList.add(new TraineeDto(t.getId(), t.getLastName(), t.getForename(), null, null, null, null, null));
        }
        return traineeDTOList;

    }

    @Transactional
    public void deleteTrainee(Long id) {
        this.entityManager.remove(this.entityManager.find(Trainee.class, id));
    }
}
