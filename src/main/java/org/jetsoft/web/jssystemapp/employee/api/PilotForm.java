package org.jetsoft.web.jssystemapp.employee.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.jetsoft.web.jssystemapp.flight.application.FlightEmployeeRowDto;

import java.util.List;

public class PilotForm {

    private Long pilotId;
    @NotBlank
    private String licenseNumber;
    @NotNull
    private Long nationalityId;
    @NotNull
    private Long statusId;
    @NotNull
    private Long hoursOfExperience;
    private List<FlightEmployeeRowDto> assignedFlights;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getHoursOfExperience() {
        return hoursOfExperience;
    }

    public void setHoursOfExperience(Long hoursOfExperience) {
        this.hoursOfExperience = hoursOfExperience;
    }

    public List<FlightEmployeeRowDto> getAssignedFlights() {
        return assignedFlights;
    }

    public void setAssignedFlights(List<FlightEmployeeRowDto> assignedFlights) {
        this.assignedFlights = assignedFlights;
    }

    public Long getPilotId() {
        return pilotId;
    }

    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }
}
