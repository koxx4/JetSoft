package org.jetsoft.web.jssystemapp.employee.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeAccountQueries;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeBasicInfoDto;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeFirstAndLastNameDto;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeQueries;
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
    public boolean employeeExists(Long id) {
        return exists(id);
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
