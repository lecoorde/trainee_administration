package com.btc.lecoorde.trainee_administration.model.dto;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class SkillDto {

    private Long id;

    private String name;

    private String description;

    public SkillDto() {
    }

    public SkillDto(Long id, String name, String description) {
        this.id = id;
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
}