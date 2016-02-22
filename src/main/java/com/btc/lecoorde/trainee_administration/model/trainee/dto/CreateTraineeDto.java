package com.btc.lecoorde.trainee_administration.model.trainee.dto;

import java.util.Date;

/**
 * Created by LECOORDE on 22.02.2016.
 */
public class CreateTraineeDto extends TraineeDTO {
    public CreateTraineeDto(Long id, String lastName, String forename, String jobName, Date birthday, Date start_of_training) {
        super(id, lastName, forename, jobName, birthday, start_of_training);
    }

    public CreateTraineeDto() {
    }
private int jobOrdinal;

    public int getJobOrdinal() {
        return jobOrdinal;
    }

    public void setJobOrdinal(int jobOrdinal) {
        this.jobOrdinal = jobOrdinal;
    }

    private long locationId;
    private long departmentId;

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

    @Override
    public String toString() {
        return "CreateTraineeDto{" +
                "locationId=" + locationId +
                ", departmentId=" + departmentId +
                "} " + super.toString();
    }
}
