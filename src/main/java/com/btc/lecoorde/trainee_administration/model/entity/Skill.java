package com.btc.lecoorde.trainee_administration.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by DESIMON on 06.01.2016.
 */
@Entity
@Table(name = "Skill")
public class Skill {

    @Id
    @SequenceGenerator(name = "skill_seq", sequenceName = "skill_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "skill_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonManagedReference
    @ManyToMany(mappedBy = "skillList")
    private Set<Trainee> trainees;

    public Skill() {

    }

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
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
        return id + ". " + name + ": " + description;
    }
}

