package org.jetsoft.web.jssystemapp.employee.application;

import org.jetsoft.web.jssystemapp.employee.api.EmployeeFilterForm;
import org.jetsoft.web.jssystemapp.employee.api.EmployeeForm;
import org.jetsoft.web.jssystemapp.employee.domain.Employee;
import org.jetsoft.web.jssystemapp.employee.domain.EmployeeAccountData;
import org.jetsoft.web.jssystemapp.employee.domain.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeAccountRepository employeeAccountRepository;
    private final EmployeeRoleRepository employeeRoleRepository;
    private final EmployeeAccountQueries employeeAccountQueries;
    private final EmployeeQueries employeeQueries;

    @Autowired
    EmployeeService(EmployeeRepository employeeRepository,
                    EmployeeAccountRepository employeeAccountRepository,
                    EmployeeRoleRepository employeeRoleRepository,
                    EmployeeAccountQueries employeeAccountQueries,
                    EmployeeQueries employeeQueries) {

        this.employeeRepository = employeeRepository;
        this.employeeAccountRepository = employeeAccountRepository;
        this.employeeRoleRepository = employeeRoleRepository;
        this.employeeAccountQueries = employeeAccountQueries;
        this.employeeQueries = employeeQueries;
    }

    public List<EmployeeBasicInfoDto> getEmployeeBasicInfoDtoListFiltered(EmployeeFilterForm filterForm) {

        return employeeQueries.getEmployeeBasicInfoDtoList().stream()
                .filter(employee -> filterEmployeeByFirstName(employee, filterForm.getFirstName()))
                .filter(employee -> filterEmployeeByLastName(employee, filterForm.getLastName()))
                .toList();
    }

    public void saveEmployeeFromForm(EmployeeForm form) {

        employeeRepository.find(form.getEmployeeId())
                .ifPresentOrElse(
                        employee -> updateEmployeeAndAccount(employee, form),
                        () -> createNewEmployee(form));
    }

    public EmployeeForm getEmployeeFormFilledFromEntity(Long employeeId) {

        var employee = employeeRepository.get(employeeId);

        return createEmployeeFormFromEmployee(employee, employeeId);
    }

    private void updateEmployeeAndAccount(Employee employee, EmployeeForm form) {

        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setEmploymentDate(form.getEmploymentDate());

        EmployeeAccountData account = employeeAccountRepository.get(employee.getId());
        List<EmployeeRole> roles = form.getEmployeeRolesId().stream()
                .map(employeeRoleRepository::get)
                .toList();

        if (!isEmpty(form.getPassword())) {

            account.setPassword(form.getPassword());
        }

        account.setLogin(form.getLogin());
        account.setEmployeeRole(roles);

        employeeAccountRepository.save(account);
        employeeRepository.save(employee);
    }

    private void createNewEmployee(EmployeeForm form) {

        List<EmployeeRole> roles = form.getEmployeeRolesId().stream()
                .map(employeeRoleRepository::get)
                .toList();

        EmployeeAccountData account = new EmployeeAccountData(form.getLogin(), form.getPassword());
        account.setEmployeeRole(roles);

        employeeAccountRepository.save(account);

        Employee employee = new Employee(
                account.getId(),
                form.getFirstName(),
                form.getLastName(),
                form.getEmploymentDate());

        employee.setId(account.getId());

        employeeRepository.save(employee);
    }

    private EmployeeForm createEmployeeFormFromEmployee(Employee employee, Long accountId) {

        List<Long> roleIdList = employeeAccountQueries.getEmployeeRoleIdListByAccountId(accountId);
        String login = employeeAccountQueries.getEmployeeLoginByAccountId(accountId);

        var form = new EmployeeForm();

        form.setEmployeeId(employee.getId());
        form.setLastName(employee.getLastName());
        form.setFirstName(employee.getFirstName());
        form.setLogin(login);
        form.setEmploymentDate(employee.getEmploymentDate());
        form.setEmployeeRolesId(roleIdList);

        return form;
    }

    private boolean filterEmployeeByFirstName(EmployeeBasicInfoDto employee, String firstName) {

        if (isEmpty(firstName)) {
            return true;
        }

        return employee.firstName().startsWith(firstName);
    }

    private boolean filterEmployeeByLastName(EmployeeBasicInfoDto employee, String lastName) {

        if (isEmpty(lastName)) {
            return true;
        }

        return employee.lastName().startsWith(lastName);
    }
}
