package com.btc.lecoorde.trainee_administration.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DESIMON on 12.01.2016.
 */

@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "department_seq")
    @Column(name = "ID")
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @JsonManagedReference
    @OneToMany(targetEntity = Trainee.class, mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Trainee> trainees = new HashSet<>();

    public Department() {

    }

    public Department(String name, String description) {

        this.name = name;
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.name + ": " + this.description;
    }


}
