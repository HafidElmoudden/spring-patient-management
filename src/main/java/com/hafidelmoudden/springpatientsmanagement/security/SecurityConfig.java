package com.hafidelmoudden.springpatientsmanagement.security;

import lombok.AllArgsConstructor;
import com.hafidelmoudden.springpatientsmanagement.security.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;
    UserDetailServiceImpl userDetailServiceImpl;

    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN", "USER").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Allow access to login page for everyone
        httpSecurity.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/user/index", true).permitAll());
        httpSecurity.rememberMe(Customizer.withDefaults());
        // Allow access to bootstrap
        httpSecurity.authorizeHttpRequests(ar -> ar.requestMatchers("/webjars/**").permitAll());
        
        // Role-based access control
        httpSecurity.authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").hasRole("USER"));
        httpSecurity.authorizeHttpRequests(ar -> ar.requestMatchers("/admin/**").hasRole("ADMIN"));
        
        // Require authentication for all other requests
        httpSecurity.authorizeHttpRequests(ar -> ar.anyRequest().authenticated());
        httpSecurity.exceptionHandling(exception -> exception.accessDeniedPage("/notAuthorized"));
        
        // Use custom UserDetailsService for authentication
        httpSecurity.userDetailsService(userDetailServiceImpl);
        return httpSecurity.build();
    }
}
