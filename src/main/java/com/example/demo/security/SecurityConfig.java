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
				.antMatchers("/cursos/**", "/matricula/**").hasAnyAuthority("ROL_ADMIN","ROL_ALUMNO")
				.antMatchers("/profesores/**").hasAnyAuthority("ROL_ADMIN", "ROL_PROFESOR")
				.antMatchers("/alumnos/**").hasAnyAuthority("ROL_ADMIN", "ROL_ALUMNO")
				.antMatchers("/adminPage/**", "/noticias/**").hasAuthority("ROL_ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/auth/login")
				.defaultSuccessUrl("/home")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll().
					logoutUrl("/auth/logout").
					logoutSuccessUrl("/auth/login?logout"));

		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}