package org.jetsoft.web.jssystemapp.employee.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntity;

import java.time.LocalDate;

@Entity
@Table(schema = "ppd")
@Access(AccessType.FIELD)
public class Employee extends AbstractEntity {

    private String firstName;
    private String lastName;
    private LocalDate employmentDate;

    public Employee(String firstName, String lastName, LocalDate employmentDate) {

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
}
