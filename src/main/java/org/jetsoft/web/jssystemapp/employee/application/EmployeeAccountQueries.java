package org.jetsoft.web.jssystemapp.employee.application;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface EmployeeAccountQueries {

    String getEmployeeLoginByAccountId(Long id);
    Long getEmployeeAccountIdByEmployeeId(Long id);
    List<String> getEmployeeRoleNamesByAccountId(Long id);
    List<Long> getEmployeeRoleIdListByAccountId(Long id);
    boolean exists(Long id);
    Optional<UserDetails> getUserDetailsForAuthenticationByLogin(String login);
}
