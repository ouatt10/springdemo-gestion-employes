package edu.isetjb.l2dsi.envdev.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build(),

            User.withUsername("employe")
                .password("{noop}emp123")
                .roles("EMPLOYE")
                .build()
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Page de login accessible à tous
                .requestMatchers("/login.html").permitAll()
                
                // API REST accessible aux deux rôles (GET seulement)
                .requestMatchers("/employes").hasAnyRole("ADMIN", "EMPLOYE")
                .requestMatchers("/employes/**").hasAnyRole("ADMIN", "EMPLOYE")
                
                // Pages Admin uniquement
                .requestMatchers("/admin.html", "/ajouter-employe.html").hasRole("ADMIN")
                
                // Page Employé
                .requestMatchers("/employe.html").hasRole("EMPLOYE")
                
                // H2 Console
                .requestMatchers("/h2-console/**").permitAll()
                
                // Tout le reste nécessite authentification
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .formLogin(form -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    // Redirection selon le rôle
                    if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                        response.sendRedirect("/admin.html");
                    } else {
                        response.sendRedirect("/employe.html");
                    }
                })
                .failureUrl("/login.html?error=true")
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html?logout=true")
                .permitAll()
            );

        return http.build();
    }
}