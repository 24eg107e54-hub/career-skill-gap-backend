
package com.careerskillgap.backend.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of("*"));

        configuration.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "PATCH",
                "DELETE", "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(formLogin -> formLogin.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/error",
                    "/api/auth/**",
                    "/api/education",
                    "/api/education/**",
                    "/api/skills",
                    "/api/skills/**",
                    "/api/projects",
                    "/api/projects/**",
                    "/api/certifications",
                    "/api/certifications/**",
                    "/api/careers",
                    "/api/careers/**",
                    "/api/recommendations",
                    "/api/recommendations/**",
                    "/api/progress",
                    "/api/progress/**",
                    "/api/skill-gap",
                    "/api/skill-gap/**",
                    "/api/career-skills",
                    "/api/career-skills/**"
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
