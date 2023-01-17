package org.jetsoft.web.jssystemapp.employee.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

@Entity
@Table(schema = "ppd")
@Access(AccessType.FIELD)
public class EmployeeRole extends AbstractEntityWithGeneratedId {

    private String role;

    public EmployeeRole(String name) {
        this.role = name;
    }

    private EmployeeRole() {}

    public String getRole() {
        return role;
    }

    public void setRole(String name) {
        this.role = name;
    }
}
