package org.jetsoft.web.jssystemapp.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.jetsoft.web.jssystemapp.configuration.security.CommonSecurityConfig.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("dev")
class HttpSecurityDevConfiguration {

    private static final String H2_CONSOLE_URL = "h2-console/**";
    private final CommonSecurityConfig commonSecurityConfig;

    HttpSecurityDevConfiguration(CommonSecurityConfig commonSecurityConfig) {

        this.commonSecurityConfig = commonSecurityConfig;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        System.out.println("READING DEV SECURITY CONFIG!");

        http.authorizeHttpRequests(this::authorizeCommonEndpoints)
                .httpBasic(withDefaults());

        http.logout().permitAll().logoutSuccessUrl("/");
        http.formLogin().permitAll();
        http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring().requestMatchers(H2_CONSOLE_URL);
    }

    private void authorizeCommonEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {

        auth
                .requestMatchers(commonSecurityConfig.otherEndpointsRequiringAuthorization()).authenticated()
                .requestMatchers(commonSecurityConfig.employeeEndpoints()).hasAuthority(HEAD_MANAGER_ROLE)
                .requestMatchers(commonSecurityConfig.customerEndpoints()).hasAuthority(CUSTOMER_ROLE)
                .anyRequest().permitAll();
    }
}
