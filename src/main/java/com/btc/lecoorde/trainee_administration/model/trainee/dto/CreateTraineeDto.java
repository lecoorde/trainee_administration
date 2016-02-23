package com.btc.lecoorde.trainee_administration.model.trainee.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by LECOORDE on 22.02.2016.
 */
public class CreateTraineeDto {

    private Long id;

    private String lastName;

    private String forename;

    private String jobName;

    private Date birthday;

    private Date start_of_training;

    private long locationId;

    private long departmentId;

    private int jobOrdinal;

    private List<Long> skillIds;

    public CreateTraineeDto(Long id, String lastName, String forename, String jobName, Date birthday, Date start_of_training, List<Long> skillIds) {
        this.id = id;
        this.lastName = lastName;
        this.forename = forename;
        this.jobName = jobName;
        this.birthday = birthday;
        this.start_of_training = start_of_training;
        this.skillIds = skillIds;
    }

    public CreateTraineeDto() {
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

    public int getJobOrdinal() {
        return jobOrdinal;
    }

    public void setJobOrdinal(int jobOrdinal) {
        this.jobOrdinal = jobOrdinal;
    }


    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Long> skillIds) {
        this.skillIds = skillIds;
    }

    @Override
    public String toString() {
        return "CreateTraineeDto{" +
                "locationId=" + locationId +
                ", departmentId=" + departmentId +
                "}";
    }
}
