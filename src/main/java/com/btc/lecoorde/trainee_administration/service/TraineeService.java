package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.dto.TraineeDto;
import com.btc.lecoorde.trainee_administration.model.entity.JobType;
import com.btc.lecoorde.trainee_administration.model.entity.Skill;
import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.dto.SkillDto;
import com.btc.lecoorde.trainee_administration.model.dto.CreateTraineeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Denis Simon on 16.02.2016.
 */
public class TraineeService {

    private final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    LocationService locationService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    SkillService skillService;

//    public TraineeDetailDTO getTraineeById(Long id) {
//
//        logger.info("Service lädt den Auszubildenden");
//
//        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Trainee t " +
//                "where t.id = " + id, Trainee.class);
//        Trainee trainee = query.getSingleResult();
//
//        TraineeDetailDTO traineeDetailDTO;
//
//        traineeDetailDTO = new TraineeDetailDTO(trainee.getId(),
//                trainee.getLastName(),
//                trainee.getForename(),
//                trainee.getJob().getJobName(),
//                trainee.getBirthday(),
//                trainee.getStart_of_training(),
//                trainee.getDepartment().getName(),
//                trainee.getLocation().getName());
//
//        return traineeDetailDTO;


    public List<SkillDto> getSkillListByTraineeId(Long id) {

        String traineeIdParameterName = "traineeId";
        logger.info("Service lädt die Liste von Skills für ID " + id);

        TypedQuery<Skill> query = this.entityManager.createQuery(
                "select s from Trainee t " +
                "join t.skillList s " +
                "where t.id =:" + traineeIdParameterName, Skill.class);

        query.setParameter(traineeIdParameterName,id);
        List<Skill> skillList = query.getResultList();

        return skillList
                .stream()
                .map(s -> new SkillDto(s.getId(), s.getName(), s.getDescription()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional
    public void createTrainee(CreateTraineeDto createTraineeDto) {

        logger.info("Auszubildender wird persistiert...");

        Trainee trainee = new Trainee();
        trainee.setLastName(createTraineeDto.getLastName());
        trainee.setForename(createTraineeDto.getForename());
        trainee.setJob(JobType.values()[createTraineeDto.getJobOrdinal()]);
        trainee.setBirthday(createTraineeDto.getBirthday());
        trainee.setStart_of_training(createTraineeDto.getStart_of_training());
        trainee.setDepartment(departmentService.getDepartmentById(createTraineeDto.getDepartmentId()));
        trainee.setLocation(locationService.getLocationById(createTraineeDto.getLocationId()));
        Set<Skill> skillSet = new HashSet<>();
        if (createTraineeDto.getSkillIds() != null) {
            skillSet.addAll(createTraineeDto.getSkillIds()
                    .stream()
                    .map(aLong -> skillService.getSkillById(aLong))
                    .collect(Collectors.toList()));
        }
        trainee.setSkillList(skillSet);
        entityManager.persist(trainee);
        for (Skill skill : skillSet) {
            skill.getTrainees().add(trainee);
        }
    }
    @Transactional
    public void updateTrainee(CreateTraineeDto input) {

        Trainee t = this.entityManager.find(Trainee.class, input.getId());
        t.setLastName(input.getLastName());
        t.setForename(input.getForename());
        t.setJob(JobType.values()[input.getJobOrdinal()]);
        t.setBirthday(input.getBirthday());
        t.setStart_of_training(input.getStart_of_training());
        t.setDepartment(departmentService.getDepartmentById(input.getDepartmentId()));
        t.setLocation(locationService.getLocationById(input.getLocationId()));
        Set<Skill> skillSet = new HashSet<>();
        if (input.getSkillIds() != null) {
            skillSet.addAll(input.getSkillIds()
                    .stream()
                    .map(aLong -> skillService.getSkillById(aLong))
                    .collect(Collectors.toList()));
        }
        t.setSkillList(skillSet);
    }

    public List<TraineeDto> getAllTrainees() {

        logger.info("Service lädt die Liste von Auzubildenden");

        TypedQuery<Trainee> query = this.entityManager.createQuery(
                "select t from Trainee t " +
                "order by t.id", Trainee.class);
        List<Trainee> traineeList = query.getResultList();
        return traineeList
                .stream()
                .map(t -> new TraineeDto(t.getId(),
                        t.getLastName(),
                        t.getForename(),
                        t.getJob().getJobName(),
                        t.getBirthday(),
                        t.getStart_of_training(),
                        t.getDepartment().getName(),
                        t.getLocation().getName()))
                .collect(Collectors.toCollection(LinkedList::new));
    }
    @Transactional
    public void deleteTrainee(Long id) {
        this.entityManager.remove(this.entityManager.find(Trainee.class, id));
    }
}