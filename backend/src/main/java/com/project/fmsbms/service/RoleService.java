package com.project.fmsbms.service;

import java.util.Optional;

import com.project.fmsbms.entities.ERole;
import com.project.fmsbms.entities.Role;
import com.project.fmsbms.exceptions.RoleNotFoundException;

public interface RoleService {
	
	// Find role by name
	public Optional<Role> findRoleByName(ERole eRole) throws RoleNotFoundException;

	// Find role by id
	public Optional<Role> findRoleById(Integer id) throws RoleNotFoundException;
}