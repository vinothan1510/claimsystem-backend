package com.vinothan.claimsystem.util;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
	    return new JwtAuthenticationFilter();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	        .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )

	        // 🔴 IMPORTANT FIX
//	        .formLogin(form -> form.disable())
//	        .httpBasic(basic -> basic.disable())

	        .authorizeHttpRequests(auth ->
	            auth
	                .requestMatchers(
	                    "/api/users/login",
	                    "/api/users/register",
	                    "/api/users/logout"
	      
	                ).permitAll()
	                .anyRequest().authenticated()
	        )
	        .addFilterBefore(
	            jwtAuthenticationFilter(),
	            UsernamePasswordAuthenticationFilter.class
	        );

	    return http.build();
	}
    
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration config=new CorsConfiguration();
    	
    	config.setAllowedOriginPatterns(List.of("http://localhost:3000"));
    	
    	config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
    	
    	config.setAllowedHeaders(List.of("*"));
    	
    	config.setAllowCredentials(true);
    	
    	config.setMaxAge(3600L);
    	
    	UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
    	
    	source.registerCorsConfiguration("/**", config);
    	
    	return source;
    }
       
}








