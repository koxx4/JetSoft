package org.jetsoft.web.jssystemapp.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CommonSecurityConfig {

    private static final String[] securedEndpoints = {
            "/secured-example-endpoint"
    };
    private static final String[] employeeEndpoints = {
            "/employeeList",
            "/editEmployee",
            "/addEmployee"
    };

    public static final String HEAD_MANAGER_ROLE = "ROLE_HEAD_MANAGER";
    public static final String MANAGER_ROLE = "ROLE_MANAGER";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    public static final String PILOT_ROLE = "ROLE_PILOT";
    public static final String CUSTOMER_ROLE = "ROLE_CUSTOMER";

    @Bean
    String[] employeeEndpoints() {

        return employeeEndpoints;
    }

    @Bean
    String[] otherEndpointsRequiringAuthorization() {

        return securedEndpoints;
    }
}
