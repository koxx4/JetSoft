package org.jetsoft.web.jssystemapp.employee.infrastructure;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeRoleDto;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeRoleQueries;
import org.jetsoft.web.jssystemapp.employee.domain.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class JpaEmployeeRoleQueries extends JpaQueries<EmployeeRole> implements EmployeeRoleQueries {

    @Autowired
    JpaEmployeeRoleQueries(EntityManager entityManager) {
        super(entityManager, EmployeeRole.class);
    }

    @Override
    public List<EmployeeRoleDto> getEmployeeRoleDtoList() {

        return getAll().stream()
                .map(employeeRole -> new EmployeeRoleDto(employeeRole.getId(), employeeRole.getRole()))
                .toList();
    }

    @Override
    public String getRoleNameByRoleId(Long id) {

        return  getById(id).getRole();
    }
}
