package org.jetsoft.web.jssystemapp.flight.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

import java.time.LocalDateTime;

@Entity
@Table(schema = "data", name = "flight_pilot")
@Access(AccessType.FIELD)
public class PilotToFlight extends AbstractEntityWithGeneratedId {

    private Long pilotId;
    private Long flightId;
    private Long assignedByEmployeeId;
    private LocalDateTime assignedDate;

    private PilotToFlight() {}

    public PilotToFlight(Long pilotId, Long flightId, Long assignedByEmployeeId, LocalDateTime assignedDate) {
        this.pilotId = pilotId;
        this.flightId = flightId;
        this.assignedByEmployeeId = assignedByEmployeeId;
        this.assignedDate = assignedDate;
    }

    public Long getPilotId() {
        return pilotId;
    }

    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getAssignedByEmployeeId() {
        return assignedByEmployeeId;
    }

    public void setAssignedByEmployeeId(Long assignedByEmployeeId) {
        this.assignedByEmployeeId = assignedByEmployeeId;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }
}