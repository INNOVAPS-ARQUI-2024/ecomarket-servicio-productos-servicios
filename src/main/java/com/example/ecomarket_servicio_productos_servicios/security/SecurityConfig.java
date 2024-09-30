package com.example.ecomarket_servicio_productos_servicios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final FirebaseAuthenticationFilter firebaseAuthenticationFilter;

    public SecurityConfig(FirebaseAuthenticationFilter firebaseAuthenticationFilter) {
        this.firebaseAuthenticationFilter = firebaseAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF si no es necesario
            .authorizeHttpRequests(auth -> auth
                // Definir rutas públicas
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/reseñas/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/servicios/**").permitAll()
                // Definir las rutas que requieren autenticación
                .requestMatchers("/api/pedidos/**").authenticated()
                .requestMatchers("/api/transacciones/**").authenticated()
                .requestMatchers("/api/notificaciones/**").authenticated()
                // Cualquier otra solicitud debe ser autenticada
                .anyRequest().permitAll())
            .addFilterBefore(firebaseAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Agregar filtro de Firebase
        return http.build();
    }
}
