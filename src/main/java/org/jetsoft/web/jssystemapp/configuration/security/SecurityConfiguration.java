package org.jetsoft.web.jssystemapp.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
class SecurityConfiguration {

    private final static List<String> securedEndpoints = List.of(
            "/secured-example-endpoint"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(SecurityConfiguration::authorizeCommonEndpoints)
                .httpBasic(withDefaults())
                .cors().disable();

        return http.build();
    }

    private static void authorizeCommonEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {

        //Oprócz tych
        auth
                .requestMatchers(securedEndpoints.toArray(String[]::new)).authenticated()
                .anyRequest().permitAll();
    }
}
