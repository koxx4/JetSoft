package org.jetsoft.web.jssystemapp.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonSecurityConfig {

    private static final String[] securedEndpoints = {
            "/secured-example-endpoint"
    };
    private static final String[] employeeEndpoints = {
            "/employeeList",
            "/editEmployee",
            "/addEmployee"
    };

    private static final String[] customerEndpoints = {
            "/customer/**"
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
    String[] customerEndpoints() {

        return customerEndpoints;
    }

    @Bean
    String[] otherEndpointsRequiringAuthorization() {

        return securedEndpoints;
    }

    @Bean
    AuthenticationManager authenticationManagerBean(
            HttpSecurity http,
            JpaUserDetailsService jpaUserDetailsService,
            PasswordEncoder passwordEncoder) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        return authenticationManagerBuilder
                .userDetailsService(jpaUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }
}
