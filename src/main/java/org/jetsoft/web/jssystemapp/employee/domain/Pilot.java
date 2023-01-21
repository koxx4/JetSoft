package org.jetsoft.web.jssystemapp.employee.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.JpaEntity;
import org.jetsoft.web.jssystemapp.flight.domain.PilotToFlight;

import java.util.List;

@Entity
@Table(schema = "ppd")
@Access(AccessType.FIELD)
public class Pilot implements JpaEntity {

    @Id
    private Long id;
    private Long nationalityId;
    private Long pilotStatusId;
    private String licenseNumber;
    private Long hoursFlown;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pilotId")
    private List<PilotToFlight> flights;

    private Pilot() {}

    public Pilot(Long id, Long nationalityId, Long pilotStatusId, String licenseNumber, Long hoursFlown, List<PilotToFlight> flights) {
        this.id = id;
        this.nationalityId = nationalityId;
        this.pilotStatusId = pilotStatusId;
        this.licenseNumber = licenseNumber;
        this.hoursFlown = hoursFlown;
        this.flights = flights;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long value) {
        id = value;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Long getPilotStatusId() {
        return pilotStatusId;
    }

    public void setPilotStatusId(Long pilotStatusId) {
        this.pilotStatusId = pilotStatusId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Long getHoursFlown() {
        return hoursFlown;
    }

    public void setHoursFlown(Long hoursFlown) {
        this.hoursFlown = hoursFlown;
    }

    public List<PilotToFlight> getFlights() {
        return flights;
    }

    public void setFlights(List<PilotToFlight> flights) {
        this.flights = flights;
    }
}
