package com.btc.lecoorde.trainee_administration.model.trainee.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by LECOORDE on 22.02.2016.
 */
public class CreateTraineeDto extends TraineeDTO {
    private List<Long> skillIds;
    private int jobOrdinal;
    private long locationId;
    private long departmentId;

    public CreateTraineeDto(Long id, String lastName, String forename, String jobName, Date birthday, Date start_of_training) {
        super(id, lastName, forename, jobName, birthday, start_of_training);
    }

    public CreateTraineeDto() {
    }

    @Override
    public String toString() {
        return "CreateTraineeDto{" +
                "locationId=" + locationId +
                ", departmentId=" + departmentId +
                "} " + super.toString();
    }

    public List<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Long> skillIds) {
        this.skillIds = skillIds;
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
}
