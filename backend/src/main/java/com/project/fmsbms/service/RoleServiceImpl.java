package com.project.fmsbms.service;

import java.util.Optional;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fmsbms.entities.ERole;
import com.project.fmsbms.entities.Role;
import com.project.fmsbms.exceptionmessages.QueryMapper;
import com.project.fmsbms.exceptions.RoleNotFoundException;
import com.project.fmsbms.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	private static Logger loggers = LogManager.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleRepository repo;

	// Method to find a role by its name
	@Override
	public Optional<Role> findRoleByName(ERole eRole) throws RoleNotFoundException {
		Optional<Role> r = repo.findByName(eRole);
		loggers.info(QueryMapper.ROLE_FOUND_BY_NAME);
		return r;
	}

	// Method to find a role by its ID
	@Override
	public Optional<Role> findRoleById(Integer id) throws RoleNotFoundException {
		Optional<Role> role = repo.findById(id);
		loggers.info(QueryMapper.ROLE_FOUND);
		return role;
	}
}