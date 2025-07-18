package com.gustavo.ecommerce.controller;

import com.gustavo.ecommerce.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationService authService;

	public AuthController(AuthenticationService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Map<String, String> body) {
		String username = body.get("username");
		String password = body.get("password");
		String token = authService.authenticate(username, password);
		return Map.of("token", token);
	}
}