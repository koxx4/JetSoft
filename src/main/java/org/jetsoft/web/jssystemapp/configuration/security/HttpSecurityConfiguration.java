package org.jetsoft.web.jssystemapp.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.jetsoft.web.jssystemapp.configuration.security.CommonSecurityConfig.HEAD_MANAGER_ROLE;
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

        http.formLogin();

        return http.build();
    }

    private void authorizeCommonEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {

        auth
                .requestMatchers(commonSecurityConfig.otherEndpointsRequiringAuthorization()).authenticated()
                .requestMatchers(commonSecurityConfig.employeeEndpoints()).hasRole(HEAD_MANAGER_ROLE)
                .anyRequest().permitAll();
    }
}
