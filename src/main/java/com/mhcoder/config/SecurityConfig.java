package com.mhcoder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // ✅ Password Encoder (IMPORTANT)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Authentication Manager (optional but useful)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // ✅ Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())   // disable csrf for API

                .authorizeHttpRequests(auth -> auth

                        // ✅ Public APIs
                        .requestMatchers("/auth/login", "/users/register").permitAll()

                        // ✅ Role based access
                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/users/allUser").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/users/**").hasRole( "ADMIN")


                        // ✅ बाकी सब secure
                        .anyRequest().authenticated()
                )

                // ✅ Session stateless (important for API)
                // ✅ Session MUST
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )

                // ✅ Save authentication in session
                .securityContext(context ->
                        context.requireExplicitSave(false)
                );

        return http.build();
    }



}
