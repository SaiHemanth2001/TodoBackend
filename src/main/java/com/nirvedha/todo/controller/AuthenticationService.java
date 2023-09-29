package com.nirvedha.todo.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nirvedha.todo.config.JwtService;
import com.nirvedha.todo.model.Role;
import com.nirvedha.todo.model.User;
import com.nirvedha.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.var;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register (RegisterRequest request) {
		var user = User.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		
		
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
				
	}
	
	public String authenticate (AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
						)
				);
		var user = repository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return jwtToken;
	}
	

}
