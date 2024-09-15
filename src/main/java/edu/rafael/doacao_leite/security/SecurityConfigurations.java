package edu.rafael.doacao_leite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityfilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/count/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("DOADOR", "RECEBEDOR")
                        .requestMatchers(HttpMethod.PUT, "/users/update").hasAnyRole("DOADOR", "RECEBEDOR")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders/count/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders/id").hasAnyRole("DOADOR", "RECEBEDOR")
                        .requestMatchers(HttpMethod.POST, "/orders/create").hasRole("RECEBEDOR")
                        .requestMatchers(HttpMethod.PUT, "/orders/update").hasAnyRole("DOADOR", "RECEBEDOR")
                        .requestMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders/status/**").hasRole("DOADOR")
                        .requestMatchers(HttpMethod.GET, "/orders/receiver/**").hasRole("RECEBEDOR")
                        .requestMatchers(HttpMethod.GET, "/orders/donor/**").hasRole("DOADOR")
                        .anyRequest().authenticated()
                        //.anyRequest().permitAll()
                )
                .addFilterBefore(securityfilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
