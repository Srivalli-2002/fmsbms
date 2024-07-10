package com.project.fmsbms.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.fmsbms.entities.ERole;
import com.project.fmsbms.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
	public Optional<Role> findByName(ERole name);
	
}
