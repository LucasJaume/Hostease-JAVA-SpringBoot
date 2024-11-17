package com.hostease.tallerHostease.config;

import com.hostease.tallerHostease.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class SecurityBeansInjector implements WebMvcConfigurer {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private UsuarioRepository usuarioRepository;



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Origen permitido
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    @Bean
    public AuthenticationManager authenticationManager() throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
        authenticationStrategy.setPasswordEncoder(passwordEncoder());
        authenticationStrategy.setUserDetailsService(userDetailsService());

        return authenticationStrategy;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (username) -> {
            return usuarioRepository.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + username));
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider daoAuthProvider) throws Exception {
        SecurityFilterChain filterChain = http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authReqConfig -> {
                    buildRequestMatchers(authReqConfig);
                })
                .build();
        return filterChain;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig){
        //Autorizacion de endpoints
        //Permitir que cualquiera pueda acceder a login y registrarse
        authReqConfig.requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
        //Modificacion de usuario
        authReqConfig.requestMatchers(HttpMethod.PUT, "/api/Usuario/edit/**").authenticated();
        //Crear Servicio
        authReqConfig.requestMatchers(HttpMethod.POST, "/api/Servicio/crear").hasRole("ADMINISTRADOR");
        //editar Servicio
        authReqConfig.requestMatchers(HttpMethod.PUT, "/api/Servicio/editar/**").hasRole("ADMINISTRADOR");
        //Eliminar Servicio
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/api/Servicio/delete/**").hasRole("ADMINISTRADOR");
        //Crear Hospedaje
        authReqConfig.requestMatchers(HttpMethod.POST, "/api/Hospedaje/Crear").hasRole("ANFITRION");
        //Editar Hospedaje
        authReqConfig.requestMatchers(HttpMethod.PUT, "/api/Hospedaje/edit/**").hasRole("ANFITRION");
        //Borrar Hospedaje
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/api/Hospedaje/Delete/**").hasRole("ANFITRION");
        //Hacer reserva
        authReqConfig.requestMatchers(HttpMethod.POST, "/api/Reserva/Crear").hasRole("INQUILINO");
        //Editar reserva
        authReqConfig.requestMatchers(HttpMethod.PUT, "/api/Reserva/edit/**").hasRole("INQUILINO");
        //Eliminar reserva
        authReqConfig.requestMatchers(HttpMethod.DELETE, "/api/Reserva/Delete/**").hasRole("INQUILINO");
    }

}
