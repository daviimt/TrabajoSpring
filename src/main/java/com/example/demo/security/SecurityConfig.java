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
				.antMatchers("/", "/home", "/auth/**", "/about/**", "/error/**", "/webjars/**",
						"/css/**", "/files/**", "/images/**", "/photos/**")
				.permitAll()
				.antMatchers("/alumnos/formAlumno/**", "/cursos/listCursosAlumno", "/cursos/filtroCursosBasicos",
						"/cursos/filtroCursosMedios", "/cursos/filtroCursosAvanzados", "/comentario/formComentario/**")
				.hasAuthority("ROL_ALUMNO")
				.antMatchers("/profesores/formProfesor/**", "/cursos/filtroCursosFechas/**",
						"/cursos/filtroCursosAcabados", "/cursos/filtroCursosSinEmpezar",
						"/cursos/filtroCursosImpartiendose", "/cursos/formCursoProfesor/**",
						"/cursos/deleteCursoProfesor/**")
				.hasAuthority("ROL_PROFESOR")
				.antMatchers("/profesores/listProfesores", "/profesores/formProfesor/**",
						"/profesores/deleteProfesor/**", "/cursos/listCursos", "/cursos/cursosRanking",
						"/cursos/formCurso/**", "/cursos/deleteCurso/**", "/alumnos/listAlumnos",
						"/alumnos/deleteAlumno/**", "/alumnos/activarUsuario/**", "/alumnos/ordenarAlumnos",
						"/noticias/listNoticias", "/noticias/formNoticia/**", "/noticias/deleteNoticia/**")
				.hasAuthority("ROL_ADMIN")
				.antMatchers("/cursos/inscritos/**", "/cursos/ordenarNotas/**")
				.hasAnyAuthority("ROL_PROFESOR", "ROL_ADMIN").antMatchers("/alumnos/**", "/matricula/**")
				.hasAnyAuthority("ROL_ADMIN", "ROL_PROFESOR", "ROL_ALUMNO")
				.antMatchers("/profesores/addProfesor", "/profesores/formProfesor/**")
				.hasAnyAuthority("ROL_ADMIN", "ROL_PROFESOR")
				.antMatchers("/profesores/listProfesores", "/profesores/deleteProfesor/**", "/profesores/formProfesor/")
				.hasAuthority("ROL_ADMIN").antMatchers("/alumnos/addAlumno", "/alumnos/formAlumno/**")
				.hasAnyAuthority("ROL_ADMIN", "ROL_ALUMNO")
				.antMatchers("/alumnos/listAlumnos", "/alumnos/deleteAlumno/**", "/alumnos/formAlumno/")
				.hasAuthority("ROL_ADMIN").antMatchers("/adminPage/**").hasAuthority("ROL_ADMIN").anyRequest()
				.authenticated())
				.formLogin((form) -> form.loginPage("/auth/login").defaultSuccessUrl("/home").permitAll())
				.logout((logout) -> logout.permitAll().logoutUrl("/auth/logout")
						.logoutSuccessUrl("/auth/login?logout"));

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}