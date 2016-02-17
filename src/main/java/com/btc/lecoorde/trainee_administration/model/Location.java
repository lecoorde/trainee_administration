package com.btc.lecoorde.trainee_administration.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DESIMON on 06.01.2016.
 */

@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @SequenceGenerator(name = "location_seq", sequenceName = "location_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "location_seq")
    @Column(name="ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name="STREET")
    private String street;

    @Column(name="HOUSENUM")
    private int houseNum;

    @Column(name = "POSTCODE")
    private int postCode;

    @Column(name="CITY")
    private String city;

    @JsonManagedReference
    @OneToMany(targetEntity = Trainee.class, mappedBy = "location", fetch = FetchType.LAZY)
    private Set<Trainee> trainees = new HashSet<>();


    public Location(){

    }

    public Location(String name, String street, int houseNum, int postCode, String city){

        this.name = name;
        this.street = street;
        this.houseNum = houseNum;
        this.postCode = postCode;
        this.city = city;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode(){
        return this.postCode;
    }

    public void setPostCode(int postCode){
        this.postCode = postCode;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    @Override
    public String toString() {
        return this.street+" "+this.houseNum+", "+this.city;
    }

}
