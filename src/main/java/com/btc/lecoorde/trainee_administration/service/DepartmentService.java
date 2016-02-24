package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.department.dto.DepartmentDto;
import com.btc.lecoorde.trainee_administration.model.entity.Trainee;
import com.btc.lecoorde.trainee_administration.model.trainee.dto.TraineeDto;
import com.btc.lecoorde.trainee_administration.model.entity.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class DepartmentService {

    private final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createDepartment(DepartmentDto departmentDTO) {
        Department department=new Department();
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        entityManager.persist(department);
    }


    public List<DepartmentDto> getAllDepartments() {

        logger.info("Service lädt die Liste von Abteilungen");

        TypedQuery<Department> query = this.entityManager.createQuery("select t from Department t " +
                "order by t.id", Department.class);
        List<Department> departmentList = query.getResultList();
        List<DepartmentDto> departmentDTOList = new LinkedList<>();
        for (Department d : departmentList) {
            departmentDTOList.add(new DepartmentDto(
                    d.getId(),
                    d.getName(),
                    d.getDescription()));
        }
        return departmentDTOList;
    }

    public Department getDepartmentById(Long id) {

        logger.info("Service lädt die Abteilung");

        TypedQuery<Department> query = this.entityManager.createQuery("select d from Department d " +
                "where d.id = " + id, Department.class);
        return query.getSingleResult();
    }

    public List<TraineeDto> getTraineeListForDepartmentId(Long id) {
        logger.info("Service lädt die Liste von Auszubildenden für Abteilungs-ID "+id);

        TypedQuery<Trainee> query = this.entityManager.createQuery("select t from Department d " +
                "join d.trainees t " +
                "where d.id = " + id, Trainee.class);

        List<Trainee> traineeList = query.getResultList();

        List<TraineeDto> traineeDTOList = new LinkedList<>();

        for (Trainee t : traineeList) {
            traineeDTOList.add(new TraineeDto(t.getId(), t.getLastName(), t.getForename(), null, null, null, null, null));
        }
        return traineeDTOList;
    }

    @Transactional
    public void deleteDepartment(Long id) {
        this.entityManager.remove(this.entityManager.find(Department.class, id));
    }
}
