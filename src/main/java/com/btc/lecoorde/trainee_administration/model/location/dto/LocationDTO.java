package com.btc.lecoorde.trainee_administration.model.location.dto;

import com.btc.lecoorde.trainee_administration.model.Location;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class LocationDTO {

    private Long id;

    private String name;

    private String street;

    private int houseNum;

    private int postCode;

    private String city;

    public LocationDTO(Long id, String name, String street, int houseNum, int postCode, String city) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.houseNum = houseNum;
        this.postCode = postCode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
