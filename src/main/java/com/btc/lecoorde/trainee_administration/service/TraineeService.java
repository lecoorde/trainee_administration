package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.entity.JobType;
import com.btc.lecoorde.trainee_administration.model.entity.Skill;
import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.skill.dto.SkillDTO;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.CreateTraineeDto;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDTO;
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


    public List<SkillDTO> getSkillListByTraineeId(Long id) {

        logger.info("Service lädt die Liste von Skills für ID " + id);

        TypedQuery<Skill> query = this.entityManager.createQuery("select s from Trainee t " +
                "join t.skillList s " +
                "where t.id = " + id, Skill.class);

        List<Skill> skillList = query.getResultList();

        List<SkillDTO> skillDTOList = new LinkedList<>();

        for (Skill s : skillList) {
            skillDTOList.add(new SkillDTO(s.getId(), s.getName(), s.getDescription()));
        }
        return skillDTOList;
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
            for (Long aLong : createTraineeDto.getSkillIds()) {
                skillSet.add(skillService.getSkillById(aLong));
            }
        }
        trainee.setSkillList(skillSet);
        entityManager.persist(trainee);
        for (Skill skill : skillSet) {
            skill.getTrainees().add(trainee);
        }
    }

    @Transactional
    public void deleteTrainee(Long id) {
        this.entityManager.remove(this.entityManager.find(Trainee.class, id));
    }

    public List<TraineeDTO> getAllTrainees() {

        logger.info("Service lädt die Liste von Auzubildenden");

        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Trainee t " +
                "order by t.id", Trainee.class);
        List<Trainee> traineeList = query.getResultList();
        List<TraineeDTO> traineeDTOList = new LinkedList<>();
        for (Trainee t : traineeList) {
            traineeDTOList.add(new TraineeDTO(t.getId(),
                    t.getLastName(),
                    t.getForename(),
                    t.getJob().getJobName(),
                    t.getBirthday(),
                    t.getStart_of_training(),
                    t.getDepartment().getName(),
                    t.getLocation().getName()));
        }
        return traineeDTOList;
    }
}