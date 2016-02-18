package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.entity.Location;
import com.btc.lecoorde.trainee_administration.model.location.dto.LocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class LocationService {

    private final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<LocationDTO> getAllLocations() {

        logger.info("Service lädt die Liste von Standorten");

        TypedQuery<Location> query = this.entityManager.createQuery("select l from Location l " +
                "order by l.id", Location.class);
        List<Location> locationList = query.getResultList();
        List<LocationDTO> locationDTOList = new LinkedList<>();
        for (Location l : locationList) {
            locationDTOList.add(new LocationDTO(
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
                "where l.id = " + id, Location.class); //TODO Detailansicht
        return query.getSingleResult();
    }
}
