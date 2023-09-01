package com.grupo18.nocountry.greenpoint.config;

import com.grupo18.nocountry.greenpoint.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authRequest->
                        authRequest
                                .requestMatchers("/auth/**","/token","/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/api-docs.yaml").permitAll()
                        )
                .authorizeHttpRequests(authRequest->
                        authRequest
                        .requestMatchers(HttpMethod.PUT,"/api/v1/users/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyRole("USER","ADMIN")
                )
                .authorizeHttpRequests(authRequest->
                        authRequest
                                .requestMatchers(HttpMethod.POST,"/api/v1/recycle").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/v1/redeem").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/transactions/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/recyclable/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/RecyclingPoint/**").hasAnyRole("USER", "ADMIN")
                )
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                )
                .authorizeHttpRequests(
                        authRequest -> authRequest.anyRequest().permitAll()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/api-docs.yaml");
//    }



}
