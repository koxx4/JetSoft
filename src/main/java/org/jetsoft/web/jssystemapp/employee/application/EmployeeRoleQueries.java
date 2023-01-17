package org.jetsoft.web.jssystemapp.employee.application;

import java.util.List;

public interface EmployeeRoleQueries {
    List<EmployeeRoleDto> getEmployeeRoleDtoList();
    String getRoleNameByRoleId(Long id);
}
