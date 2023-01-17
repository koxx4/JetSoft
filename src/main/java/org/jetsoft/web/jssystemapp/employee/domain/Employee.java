package org.jetsoft.web.jssystemapp.employee.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.JpaEntity;

import java.time.LocalDate;

@Entity
@Table(schema = "ppd")
@Access(AccessType.FIELD)
public class Employee implements JpaEntity {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;

    public Employee(Long id, String firstName, String lastName, LocalDate employmentDate) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
    }

    private Employee() {}

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
