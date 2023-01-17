package org.jetsoft.web.jssystemapp.employee.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeAccountQueries;
import org.jetsoft.web.jssystemapp.employee.domain.EmployeeAccountData;
import org.jetsoft.web.jssystemapp.employee.domain.EmployeeRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class JpaEmployeeAccountQueries extends JpaQueries<EmployeeAccountData> implements EmployeeAccountQueries {

    private static final String EMPTY_STRING = "";

    JpaEmployeeAccountQueries(EntityManager entityManager) {
        super(entityManager, EmployeeAccountData.class);
    }

    @Override
    public String getEmployeeLoginByAccountId(Long id) {

        return findById(id)
                .map(EmployeeAccountData::getLogin)
                .orElse(EMPTY_STRING);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getEmployeeAccountIdByEmployeeId(Long id) {

        var criteriaBuilder = getCriteriaBuilder();
        var criteriaQuery = getCriteriaQuery(criteriaBuilder);
        Root<EmployeeAccountData> root = criteriaQuery.from(EmployeeAccountData.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

        return getEntityManager().createQuery(criteriaQuery).getSingleResult().getId();
    }

    @Override
    public List<String> getEmployeeRoleNamesByAccountId(Long id) {

        return findById(id)
                .map(EmployeeAccountData::getEmployeeRole)
                .orElseThrow(EntityNotFoundException::new).stream()
                .map(EmployeeRole::getRole)
                .toList();
    }

    @Override
    public List<Long> getEmployeeRoleIdListByAccountId(Long id) {

        return findById(id)
                .map(EmployeeAccountData::getEmployeeRole)
                .orElseThrow(EntityNotFoundException::new).stream()
                .map(EmployeeRole::getId)
                .toList();
    }
}
