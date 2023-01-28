package org.jetsoft.web.jssystemapp.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.jetsoft.web.jssystemapp.configuration.security.CommonSecurityConfig.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
class HttpSecurityConfiguration {

    private final CommonSecurityConfig commonSecurityConfig;

    HttpSecurityConfiguration(CommonSecurityConfig commonSecurityConfig) {

        this.commonSecurityConfig = commonSecurityConfig;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(this::authorizeCommonEndpoints)
                .httpBasic(withDefaults());

        http.logout().permitAll().logoutSuccessUrl("/");
        http.formLogin().permitAll();

        return http.build();
    }

    private void authorizeCommonEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {

        auth
                .requestMatchers(commonSecurityConfig.otherEndpointsRequiringAuthorization()).authenticated()
                .requestMatchers(commonSecurityConfig.employeeEndpoints()).hasAnyAuthority(HEAD_MANAGER_ROLE, MANAGER_ROLE, PILOT_ROLE)
                .requestMatchers(commonSecurityConfig.customerEndpoints()).hasAuthority(CUSTOMER_ROLE)
                .anyRequest().permitAll();
    }
}
