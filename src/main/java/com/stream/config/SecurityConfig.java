package com.stream.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import com.stream.model.entities.User;
import com.stream.providers.AdminAuthenticationProvider;
import com.stream.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	AdminAuthenticationProvider adminAuthenticationProvider;
	

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return username -> {
			Optional<User> u = userRepository.findByEmail(username);
			if(u.isPresent()) {
				return u.get();
			}else {
				throw new UsernameNotFoundException(username+"not found");
			}
		};
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		
		var dao = new DaoAuthenticationProvider(passwordEncoder);
		dao.setUserDetailsService(userDetailsService);
		return dao;
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);
			authenticationManagerBuilder.authenticationProvider(adminAuthenticationProvider);
			authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider);
			return authenticationManagerBuilder.build();
	}
	
	
	

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		
		http.authorizeRequests().requestMatchers("/auth/**", "/resources/**").permitAll()
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/login")
		.loginProcessingUrl("/authenticate")
		.usernameParameter("email")
		.defaultSuccessUrl("/watch")
		.failureUrl("/auth/login?error=true")
		.and()
		.logout()
		.logoutUrl("/logout")
		.and()
		.csrf()
		.disable();
		
		http.headers().frameOptions().disable();
		
		http.authenticationManager(authManager);
	
				
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		
		
		return (web) -> {web.ignoring().requestMatchers("/favicon.ico");};
		
	}
	
}
