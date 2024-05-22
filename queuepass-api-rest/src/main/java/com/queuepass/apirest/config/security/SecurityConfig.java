package com.queuepass.apirest.config.security;

import com.queuepass.apirest.config.security.filter.JwtTokenValidator;
import com.queuepass.apirest.service.user.UserDetailServiceImpl;
import com.queuepass.apirest.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Clase de configuración de seguridad.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Configura el filtro de seguridad.
     * @param httpSecurity el objeto HttpSecurity
     * @return el SecurityFilterChain configurado
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/files/*", "/auth/*").permitAll() // Permite todas las solicitudes que coincidan con "/files/*" o "/auth/*"
                                .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                )
                .httpBasic(Customizer.withDefaults()) // Configura la autenticación básica
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura la gestión de sesiones como sin estado
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class) // Añade el filtro JwtTokenValidator antes del filtro BasicAuthenticationFilter
                .build();
    }

    /**
     * Crea el AuthenticationManager.
     * @param authenticationConfiguration la configuración de autenticación
     * @return el AuthenticationManager creado
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Crea el AuthenticationProvider.
     * @param userDetailService el UserDetailServiceImpl
     * @return el AuthenticationProvider creado
     */
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    /**
     * Crea el PasswordEncoder.
     * @return el PasswordEncoder creado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance(); PRUEBAS (PASSWORD SIN ENCRIPTAR)
        return new BCryptPasswordEncoder();
    }
}
