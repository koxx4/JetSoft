package org.jetsoft.web.jssystemapp.configuration.security;

import org.jetsoft.web.jssystemapp.employee.application.EmployeeAccountQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
class JpaUserDetailsService implements UserDetailsService {

    private final EmployeeAccountQueries employeeAccountQueries;

    @Autowired
    JpaUserDetailsService(EmployeeAccountQueries employeeAccountQueries) {
        
        this.employeeAccountQueries = employeeAccountQueries;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return employeeAccountQueries.getUserDetailsForAuthenticationByLogin(username);
    }
}
