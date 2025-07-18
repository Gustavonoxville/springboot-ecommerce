package com.gustavo.ecommerce.config;

import com.gustavo.ecommerce.entity.Role;
import com.gustavo.ecommerce.entity.User;
import com.gustavo.ecommerce.repository.RoleRepository;
import com.gustavo.ecommerce.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		Role adminRole = roleRepository.save(new Role("ADMIN"));
		Role userRole = roleRepository.save(new Role("USER"));

		if (userRepository.findByUsername("admin").isEmpty()) {
			User admin = new User("admin", passwordEncoder.encode("admin123"), Set.of(adminRole, userRole));
			userRepository.save(admin);
		}

		if (userRepository.findByUsername("user").isEmpty()) {
			User user = new User("user", passwordEncoder.encode("user123"), Set.of(userRole));
			userRepository.save(user);
		}
	}
}