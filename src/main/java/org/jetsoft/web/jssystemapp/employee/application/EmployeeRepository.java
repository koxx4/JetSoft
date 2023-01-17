package org.jetsoft.web.jssystemapp.employee.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.employee.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class EmployeeRepository extends JpaRepository<Employee> {

    @Autowired
    EmployeeRepository(EntityManager entityManager) {

        super(entityManager, Employee.class);
    }
}
