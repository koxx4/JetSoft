package org.jetsoft.web.jssystemapp.employee.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.employee.domain.EmployeeRole;
import org.springframework.stereotype.Service;

@Service
class EmployeeRoleRepository extends JpaRepository<EmployeeRole> {

    public EmployeeRoleRepository(EntityManager entityManager) {

        super(entityManager, EmployeeRole.class);
    }
}
