package org.jetsoft.web.jssystemapp.employee.application;

import java.util.List;

public interface EmployeeQueries {

    EmployeeBasicInfoDto getEmployeeBasicInfoDto(Long id);
    List<EmployeeBasicInfoDto> getEmployeeBasicInfoDtoList();
    EmployeeFirstAndLastNameDto getEmployeeFirstAndLastNameDto(Long id);
    boolean employeeExists(Long id);
}
