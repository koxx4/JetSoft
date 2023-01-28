package org.jetsoft.web.jssystemapp.employee.application;

import java.time.LocalDate;
import java.util.List;

public record EmployeeProfileDto(
        String firstName,
        String lastName,
        String login,
        LocalDate employmentDate,
        List<String> roleNames
) {}
