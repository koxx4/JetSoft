package org.jetsoft.web.jssystemapp.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("dev")
class SecurityDevConfiguration {

    private static final List<String> securedEndpoints = List.of(
            "/secured-example-endpoint"
    );

    private static final String H2_CONSOLE_URL = "h2-console/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        System.out.println("READING DEV SECURITY CONFIG!");

        http.authorizeHttpRequests(SecurityDevConfiguration::authorizeCommonEndpoints)
                .httpBasic(withDefaults());

        http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring().requestMatchers(H2_CONSOLE_URL);
    }

    private static void authorizeCommonEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {

        //Oprócz tych
        auth
                .requestMatchers(securedEndpoints.toArray(String[]::new)).authenticated()
                .anyRequest().permitAll();
    }
}
