package com.btc.lecoorde.trainee_administration.service;

import com.btc.lecoorde.trainee_administration.model.department.dto.DepartmentDTO;
import com.btc.lecoorde.trainee_administration.model.entity.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis Simon on 18.02.2016.
 */
public class DepartmentService {

    private final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Department getDepartmentById(Long id) {

        logger.info("Service lädt die Abteilung");

        TypedQuery<Department> query = this.entityManager.createQuery("select d from Department d " +
                "where d.id = " + id, Department.class); //TODO Detailansicht
        return query.getSingleResult();
    }

    public List<DepartmentDTO> getAllDepartments() {

        logger.info("Service lädt die Liste von Abteilungen");

        TypedQuery<Department> query = this.entityManager.createQuery("select t from Department t " +
                "order by t.id", Department.class);
        List<Department> departmentList = query.getResultList();
        List<DepartmentDTO> departmentDTOList = new LinkedList<>();
        for (Department d : departmentList) {
            departmentDTOList.add(new DepartmentDTO(
                    d.getId(),
                    d.getName(),
                    d.getDescription()));
        }
        return departmentDTOList;
    }
}
