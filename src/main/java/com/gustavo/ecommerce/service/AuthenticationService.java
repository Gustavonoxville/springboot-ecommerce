package com.gustavo.ecommerce.service;

import com.gustavo.ecommerce.entity.User;
import com.gustavo.ecommerce.repository.UserRepository;
import com.gustavo.ecommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	public String authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

		String roles = String.join(",", user.getRoles().stream().map(r -> r.getName()).toList());

		return jwtUtil.generateToken(username, roles);
	}
}