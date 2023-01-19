package org.jetsoft.web.jssystemapp.employee.application;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface EmployeeAccountQueries {

    String getEmployeeLoginByAccountId(Long id);
    Long getEmployeeAccountIdByEmployeeId(Long id);
    List<String> getEmployeeRoleNamesByAccountId(Long id);
    List<Long> getEmployeeRoleIdListByAccountId(Long id);
    boolean exists(Long id);
    UserDetails getUserDetailsForAuthenticationByLogin(String login);
}
