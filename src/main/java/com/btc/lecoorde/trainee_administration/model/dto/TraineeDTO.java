package com.btc.lecoorde.trainee_administration.model.dto;

import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * Created by Denis Simon on 17.02.2016.
 */
public class TraineeDto {

    private Long id;

    private String lastName;

    private String forename;

    private String jobName;

    private Date birthday;

    private Date start_of_training;

    private int jobOrdinal;

    private String departmentName;

    private Long departmentId;

    private String locationName;

    private Long locationId;

    private LinkedList<Long> skillIds;

    private LinkedList<String> skillNames;


    public TraineeDto(Long id, String lastName, String forename, String jobName, Date birthday, Date start_of_training, int jobOrdinal, String departmentName, Long departmentId, String locationName, Long locationId,LinkedList<String> skillNames, LinkedList<Long> skillIds) {
        this.id = id;
        this.lastName = lastName;
        this.forename = forename;
        this.jobName = jobName;
        this.birthday = birthday;
        this.start_of_training = start_of_training;
        this.jobOrdinal = jobOrdinal;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.locationName = locationName;
        this.locationId = locationId;
        this.skillIds = skillIds;
        this.skillNames = skillNames;
    }

    public TraineeDto() {
    }

    @Override
    public String toString() {
        return "TraineeDto{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", forename='" + forename + '\'' +
                ", jobName='" + jobName + '\'' +
                ", birthday=" + birthday +
                ", start_of_training=" + start_of_training +
                '}';
    }

    public int getJobOrdinal() {
        return jobOrdinal;
    }

    public void setJobOrdinal(int jobOrdinal) {
        this.jobOrdinal = jobOrdinal;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public LinkedList<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(LinkedList<Long> skillIds) {
        this.skillIds = skillIds;
    }

    public LinkedList<String> getSkillNames() {
        return skillNames;
    }

    public void setSkillNames(LinkedList<String> skillNames) {
        this.skillNames = skillNames;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getStart_of_training() {
        return start_of_training;
    }

    public void setStart_of_training(Date start_of_training) {
        this.start_of_training = start_of_training;
    }
}
