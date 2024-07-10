package com.project.fmsbms.controller;

import java.util.Optional;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fmsbms.entities.ERole;
import com.project.fmsbms.entities.Role;
import com.project.fmsbms.entities.UserEntity;
import com.project.fmsbms.exceptionmessages.QueryMapper;
import com.project.fmsbms.exceptions.RoleNotFoundException;
import com.project.fmsbms.security.jwt.JwtUtils;
import com.project.fmsbms.security.payload.request.LoginRequest;
import com.project.fmsbms.security.payload.request.SignupRequest;
import com.project.fmsbms.security.payload.response.JwtResponse;
import com.project.fmsbms.security.payload.response.MessageResponse;
import com.project.fmsbms.security.service.UserDetailsImpl;
import com.project.fmsbms.security.service.UserDetailsServiceImpl;
import com.project.fmsbms.service.RoleService;
import com.project.fmsbms.service.UserEntityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	private static Logger loggers = LogManager.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	UserEntityService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	// API end point for user authentication
	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		loggers.info("signin");

		// Authenticate user credentials
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String role = userDetails.getAuthorities().stream().findFirst() // Get the first authority
				.map(item -> item.getAuthority()) // Map it to its authority string
				.orElse(null);

		// Create JWT response with user details and token
		JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), role);
		return ResponseEntity.ok(jwtResponse);
	}

	// API end point for user registration
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest)
			throws RoleNotFoundException {

		loggers.info("signin");
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			loggers.error(QueryMapper.USERNAME_TAKEN);
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user entity and populate with request data
		UserEntity user = new UserEntity();
		user.setUsername(signUpRequest.getUsername());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

		// Find user role by name and assign it
		Optional<Role> role = Optional.of(roleService.findRoleByName(ERole.ROLE_USER).get());
		if (role.isPresent()) {
			user.setRole(role.get());
		}

		// Save the user entity
		userService.addUserEntity(user);
		loggers.info(QueryMapper.USERNAME_REGISTERED);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
