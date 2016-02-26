package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.TraineeAdministrationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by LECOORDE on 26.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TraineeAdministrationApplication.class)
public class LocationServiceTest {
    @Autowired
    LocationService locationService;

    @Test
    public void testCreateLocation() throws Exception {

    }

    @Test
    public void testGetLocationById() throws Exception {

    }

    @Test
    public void testDeleteLocation() throws Exception {

    }

    @Test
    public void testGetAllLocations() throws Exception {
        System.out.println(locationService.getAllLocations());
    }
}