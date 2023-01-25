package org.jetsoft.web.jssystemapp.employee.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

@Entity
@Table(schema = "ppd")
@Access(AccessType.FIELD)
public class PilotStatus extends AbstractEntityWithGeneratedId {

    private String status;

    private PilotStatus() {}

    public PilotStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean allowsPilotToBeAssignedToFlight() {

        return status.equalsIgnoreCase("AVAILABLE");
    }
}
