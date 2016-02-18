package com.btc.lecoorde.trainee_administration.model.trainee.dto;

import java.util.Date;

/**
 * Created by Denis Simon on 17.02.2016.
 */
public class TraineeDTO {

    private Long id;

    private String lastName;

    private String forename;

    private String jobName;

    private Date birthday;

    private Date start_of_training;


    public TraineeDTO(Long id, String lastName, String forename, String jobName, Date birthday, Date start_of_training) {
        this.id = id;
        this.lastName = lastName;
        this.forename = forename;
        this.jobName = jobName;
        this.birthday = birthday;
        this.start_of_training = start_of_training;
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
