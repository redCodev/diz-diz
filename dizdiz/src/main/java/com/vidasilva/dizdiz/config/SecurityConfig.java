package com.vidasilva.dizdiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                // Allow everyone to access the login and registration pages
                .requestMatchers("/login", "/register").permitAll()
                // The home page requires authentication
                .requestMatchers("/home").authenticated()
                // Any other request must be authenticated
                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                // Specify the login page
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
                )
                .logout((logout) -> logout
                .permitAll()
                ).sessionManagement((session) -> session // Configure session management
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Create session if necessary
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user
                = User.builder().username("username")
                        .password("password").roles("ADMIN", "USER", "POSTER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
