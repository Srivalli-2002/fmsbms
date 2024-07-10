package com.project.fmsbms.service;

import java.util.List;
import java.util.Optional;


import com.project.fmsbms.entities.ERole;
import com.project.fmsbms.entities.Role;
import com.project.fmsbms.entities.UserEntity;
import com.project.fmsbms.exceptions.RoleNotFoundException;

public interface UserEntityService {

	// Add user
	public UserEntity addUserEntity(UserEntity user);

	// Update role
	public String updateRole(String username, Role role) throws RoleNotFoundException;

	// Find by user name
	public Optional<UserEntity> findByUsername(String username);

	// Exists by user name
	public Boolean existsByUsername(String username);

	// Find by role
	public Optional<UserEntity> findByRole(ERole role);

	// Get all users
	public List<UserEntity> getAllUserEntities();
}