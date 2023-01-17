package org.jetsoft.web.jssystemapp.employee.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.employee.domain.EmployeeAccountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class EmployeeAccountRepository extends JpaRepository<EmployeeAccountData> {

    @Autowired
    EmployeeAccountRepository(EntityManager entityManager) {

        super(entityManager, EmployeeAccountData.class);
    }
}
