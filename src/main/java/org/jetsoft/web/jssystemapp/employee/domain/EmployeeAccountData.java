package org.jetsoft.web.jssystemapp.employee.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntity;

import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(schema = "ppd")
public class EmployeeAccountData extends AbstractEntity {

    private String login;
    private String password;

    @JoinTable(
            schema = "ppd",
            name = "role_to_employee",
            joinColumns = @JoinColumn(name = "employee_account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @OneToMany
    private List<EmployeeRole> employeeRole;

    public EmployeeAccountData(String login, String password) {

        this.login = login;
        this.password = password;
    }

    private EmployeeAccountData() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<EmployeeRole> getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(List<EmployeeRole> employeeRole) {
        this.employeeRole = employeeRole;
    }
}
