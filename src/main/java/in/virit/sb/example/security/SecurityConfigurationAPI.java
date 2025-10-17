package in.virit.sb.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurationAPI {
    // Additional security configuration for the "private" REST API
    @Bean
    @Order(10)
    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        System.err.println("Configuring private API security");

        return http
                .securityMatcher("/api/private/**")
                // Ignoring CSRF for the private API, expected to be used by other services, not
                // directly by browser clients
                .csrf(csrf -> csrf.ignoringRequestMatchers(PathPatternRequestMatcher.withDefaults().matcher("/api/private/**")))
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().authenticated();
                })
                // so session management/cookie is not needed
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // HttpStatusEntryPoint only sets status code, Location header to login page makes no sense here
                .httpBasic(cfg -> cfg.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .build();
    }

    // Then open anything for the public API for the application
    @Order(20)
    @Bean
    SecurityFilterChain configurePublicApi(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/public/**")
                .authorizeHttpRequests(authz -> authz.requestMatchers("/api/public/**").permitAll());
        return http.build();
    }
}
