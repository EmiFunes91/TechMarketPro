package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	@Lazy
	private AuthFailureHandlerImpl authenticationFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();  // Implementación personalizada de UserDetailsService
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req
						.requestMatchers("/user/**").hasRole("USER")  // Solo usuarios con rol USER
						.requestMatchers("/admin/**").hasRole("ADMIN")  // Solo usuarios con rol ADMIN
						.requestMatchers("/**").permitAll()  // Todas las demás rutas son accesibles
				)
				.formLogin(form -> form
						.loginPage("/signin")  // Página personalizada de inicio de sesión
						.loginProcessingUrl("/login")  // URL donde se procesa el inicio de sesión
						.failureHandler(authenticationFailureHandler)  // Manejo de errores de autenticación
						.successHandler(authenticationSuccessHandler)  // Manejo del éxito de autenticación
				)
				.logout(logout -> logout
						.logoutUrl("/logout")  // URL para el logout
						.logoutSuccessUrl("/")  // Redirecciona a la página principal tras el logout
						.invalidateHttpSession(true)  // Invalidar sesión tras logout
						.clearAuthentication(true)  // Limpiar autenticación
						.permitAll()  // Permitir que cualquiera se desloguee
				);

		return http.build();
	}
}

