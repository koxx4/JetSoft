package org.jetsoft.web.jssystemapp.employee.application;

import java.util.List;

public record PilotListRowDto(
        Long pilotId,
        String firstName,
        String lastName,
        String statusName,
        String nationalityName,
        String licenseNumber,
        Long hoursOfExperience,
        List<String> assignedFlightsNumbers
) {}
