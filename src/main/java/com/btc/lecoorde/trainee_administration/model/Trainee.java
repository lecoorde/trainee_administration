package com.btc.lecoorde.trainee_administration.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "TRAINEE")
public class Trainee implements Comparable<Trainee> {

    @Id
    @SequenceGenerator(name = "trainee_seq", sequenceName = "trainee_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "trainee_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "FORENAME")
    private String forename;

    @Column(name = "JOB")
    private String jobName;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "START_OF_TRAINING")
    private Date start_of_training;


    @JsonBackReference
    @ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonBackReference
    @ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;


    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Trainee_Skill",
            joinColumns = {@JoinColumn(name = "TRAINEE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SKILL_ID")})
    private Set<Skill> skillList = new HashSet<>();

    public Trainee() {

    }

    public Trainee(String lastName, String forename, Date birthday, JobType job, Date start_of_training, Department department, Location location, HashSet<Skill> skillList) {

        Date time = new Date();

        this.lastName = lastName;
        this.forename = forename;
        this.jobName = job.name();
        this.birthday = birthday;
        this.start_of_training = start_of_training;

        this.department = department;

        this.location = location;

        this.skillList = skillList;

    }

    public int compareTo(Trainee trainee) {
        if (this.getId().compareTo(trainee.getId()) > 0) {
            return 1;
        }
        if (this.getId().compareTo(trainee.getId()) == 0) {
            return 0;
        } else {
            return -1;
        }
    }



    public Date getStart_of_training() {
        return this.start_of_training;
    }

    public void setStart_of_training(Date start_of_training) {
        this.start_of_training = start_of_training;
    }

    public String getJob() {
        return this.jobName;
    }

    public void setJob(JobType jobType) {
        this.jobName = jobType.name();
    }


    //ENUMS
    //    public JobType getJob(){
//        return JobType.valueOf(this.job.toUpperCase());
//    }
//
//    public void setJob(JobType job) {
//        this.job = job.name();
//    }


    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getForename() {
        return this.forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public Set<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(Set<Skill> skillList) {
        this.skillList = skillList;
    }


    public void addSkill(Skill skill) {
        this.skillList.add(skill);

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if ((object == null) || (object.getClass() != this.getClass())) {
            return false;
        }
        Trainee trainee = (Trainee) object;
        return this.id.equals(trainee.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        SimpleDateFormat shorter = new SimpleDateFormat("dd.MM.yyyy");
        return "---- ID: " + this.id + " ---- " +
                "\n" + this.forename + " " + this.lastName + ", geboren am " + shorter.format(this.birthday) + " --- " + this.jobName + ", " +
                "\n";
    }
}
