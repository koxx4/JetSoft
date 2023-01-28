package org.jetsoft.web.jssystemapp.employee.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.employee.application.*;
import org.jetsoft.web.jssystemapp.employee.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class JpaEmployeeQueries extends JpaQueries<Employee> implements EmployeeQueries {

    private final EmployeeAccountQueries employeeAccountQueries;

    JpaEmployeeQueries(EntityManager entityManager, EmployeeAccountQueries employeeAccountQueries) {

        super(entityManager, Employee.class);
        this.employeeAccountQueries = employeeAccountQueries;
    }

    @Override
    public EmployeeBasicInfoDto getEmployeeBasicInfoDto(Long id) {

        return findById(id)
                .map(this::toEmployeeBasicInfoDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<EmployeeBasicInfoDto> getEmployeeBasicInfoDtoList() {

        return getAll().stream()
                .map(this::toEmployeeBasicInfoDto)
                .toList();
    }

    @Override
    public EmployeeFirstAndLastNameDto getEmployeeFirstAndLastNameDto(Long id) {

        Employee employee = getById(id);

        return new EmployeeFirstAndLastNameDto(employee.getFirstName(), employee.getLastName());
    }

    @Override
    public EmployeeProfileDto getEmployeeProfileDto(Long employeeId) {

        return findById(employeeId)
                .map(this::toEmployeeProfileDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean employeeExists(Long id) {
        return exists(id);
    }

    private EmployeeProfileDto toEmployeeProfileDto(Employee employee) {

        List<String> roles = employeeAccountQueries.getEmployeeRoleNamesByAccountId(employee.getId());
        String login = employeeAccountQueries.getEmployeeLoginByAccountId(employee.getId());

        return new EmployeeProfileDto(
                employee.getFirstName(),
                employee.getLastName(),
                login,
                employee.getEmploymentDate(),
                roles
        );
    }

    private EmployeeBasicInfoDto toEmployeeBasicInfoDto(Employee employee) {

        Long accountId = employeeAccountQueries.getEmployeeAccountIdByEmployeeId(employee.getId());
        String login = employeeAccountQueries.getEmployeeLoginByAccountId(accountId);

        return new EmployeeBasicInfoDto(
                employee.getId(),
                accountId,
                employee.getFirstName(),
                employee.getLastName(),
                login
        );
    }
}
