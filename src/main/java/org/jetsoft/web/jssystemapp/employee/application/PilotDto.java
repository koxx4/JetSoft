package org.jetsoft.web.jssystemapp.employee.application;

public record PilotDto(
    Long pilotId,
    String firstName,
    String lastName,
    String statusName,
    String nationalityName,
    Long hoursOfExperience
) { }
