package org.jetsoft.web.jssystemapp.configuration.security;

import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeAccountQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class JpaUserDetailsService implements UserDetailsService {

    private final EmployeeAccountQueries employeeAccountQueries;
    private final CustomerQueries customerQueries;

    @Autowired
    JpaUserDetailsService(EmployeeAccountQueries employeeAccountQueries, CustomerQueries customerQueries) {
        
        this.employeeAccountQueries = employeeAccountQueries;
        this.customerQueries = customerQueries;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserDetails> userDetailsForEmployee = employeeAccountQueries
                .getUserDetailsForAuthenticationByLogin(username);

        if (userDetailsForEmployee.isPresent()) {

            return userDetailsForEmployee.get();
        }

        return customerQueries.findCustomerAccountInfoByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username with " + username + " does not exist!"));
    }
}
