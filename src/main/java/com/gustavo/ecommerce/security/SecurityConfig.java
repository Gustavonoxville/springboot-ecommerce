package com.gustavo.ecommerce.security;

import com.gustavo.ecommerce.repository.UserRepository;
import com.gustavo.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("removal")
	@Bean
	public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder,
			UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(encoder).and().build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			User user = userRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					authorities);
		};
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/products/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/orders/**").hasRole("USER").requestMatchers("/analytics/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.addFilterBefore(new JwtFilter(jwtUtil, userDetailsService()),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}