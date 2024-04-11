package com.luanvan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private final CustomUserDetailService customUserDetailService;
	
	public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailService customUserDetailService) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.customUserDetailService = customUserDetailService;
	}

	@Bean
	SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http
    	.cors(Customizer.withDefaults())
    	.csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //.securityMatcher(AntPathRequestMatcher.antMatcher("/api/**"))
        .authorizeHttpRequests(request -> request
				.requestMatchers("/").permitAll()
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/getimage/**").permitAll()
				.requestMatchers("/ws/**").permitAll()
				.requestMatchers("/user-chat/**").permitAll()
				.requestMatchers("/admin/**").hasAnyRole("ADMIN", "SALER")
				.anyRequest().authenticated()
        );
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(customUserDetailService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
}
