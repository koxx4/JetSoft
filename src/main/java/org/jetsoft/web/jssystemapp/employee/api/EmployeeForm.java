package org.jetsoft.web.jssystemapp.employee.api;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class EmployeeForm {

    private Long employeeId;
    @NotBlank(message = "Imię nie może być puste")
    @Size(max = 100, message = "Login nie może zawierać więcej niż 100 znaków")
    private String firstName;
    @NotBlank(message = "Nazwisko nie może być puste")
    @Size(max = 100, message = "Login nie może zawierać więcej niż 100 znaków")
    private String lastName;
    @NotNull(message = "Podaj datę zatrudnienia")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate employmentDate;
    @NotBlank(message = "Login nie może być pusty")
    @Size(min = 3, message = "Login musi mieć conajmniej 3 znaki")
    @Size(max = 20, message = "Login nie może zawierać więcej niż 20 znaków")
    private String login;
    private String password;
    @NotEmpty
    private List<Long> employeeRolesId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

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

    public List<Long> getEmployeeRolesId() {
        return employeeRolesId;
    }

    public void setEmployeeRolesId(List<Long> employeeRolesId) {
        this.employeeRolesId = employeeRolesId;
    }
}
