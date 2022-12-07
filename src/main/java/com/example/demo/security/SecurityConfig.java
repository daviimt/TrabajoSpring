package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests((requests) -> requests
				.antMatchers("/", "/home", "/auth/**", "/about/**", "/noticias", "/error/**", "/webjars/**", "/css/**", "/files/**", "/imgs/**", "/photos/**").permitAll()
				.antMatchers("/cursos/**", "/matricula/**").access("hasRole('ROL_ALUMNO') or hasRole('ROLE_ADMIN')")
				.antMatchers("/alumnos/**").access("hasRole('ROL_PROFESOR') or hasRole('ROLE_ADMIN')")
				.antMatchers("/adminPage/**", "/noticias/**").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.defaultSuccessUrl("/home")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll().
					logoutUrl("/logout").
					logoutSuccessUrl("/auth/login?logout"));

		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}