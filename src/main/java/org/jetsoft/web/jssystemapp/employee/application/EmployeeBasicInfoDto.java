package org.jetsoft.web.jssystemapp.employee.application;

public record EmployeeBasicInfoDto(
        Long employeeId,
        Long accountId,
        String firstName,
        String lastName,
        String login
) {}
