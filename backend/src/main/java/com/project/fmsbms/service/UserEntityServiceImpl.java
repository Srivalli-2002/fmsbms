package com.project.fmsbms.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.fmsbms.entities.ERole;
import com.project.fmsbms.entities.Role;
import com.project.fmsbms.entities.UserEntity;
import com.project.fmsbms.exceptionmessages.QueryMapper;
import com.project.fmsbms.exceptions.RoleNotFoundException;
import com.project.fmsbms.repositories.UserRepository;

@Service
public class UserEntityServiceImpl implements UserEntityService {

	private static Logger loggers = LogManager.getLogger(UserEntityServiceImpl.class);

	@Autowired
	private UserRepository repo;
	
	


	// Method to add a user entity
	@Override
	public UserEntity addUserEntity(UserEntity user) {
		UserEntity userEntity = repo.save(user);
		loggers.info(QueryMapper.ADD_USER);
		return userEntity;
	}

	// Method to find a user by user name
	@Override
	public Optional<UserEntity> findByUsername(String username) {
		Optional<UserEntity> user = repo.findByUsername(username);
		loggers.info(QueryMapper.FIND_BY_USERNAME);
		return user;
	}

	// Method to check if a user exists by username
	@Override
	public Boolean existsByUsername(String username) {
		Boolean b = repo.existsByUsername(username);
		loggers.info(QueryMapper.EXIST_BY_USERNAME);
		return b;
	}

	// Method to find a user by role
	@Override
	public Optional<UserEntity> findByRole(ERole role) {
		Optional<UserEntity> user = repo.findByRole(role);
		loggers.info(QueryMapper.ROLE_FOUND);
		return user;
	}

	// Method to get the list of users
	@Override
	public List<UserEntity> getAllUserEntities() {
		List<UserEntity> allUsers = (List<UserEntity>) repo.findAll();
		loggers.info(QueryMapper.ROLE_FOUND);
		return allUsers;
	}

	@Override
	public String updateRole(String username, Role role) throws RoleNotFoundException {
		Optional<UserEntity> user= repo.findByUsername(username);
		if(user.isPresent())
		{
			user.get().setRole(role);
			repo.save(user.get());
			return "Role Updated Successfully";
		}
		else throw new UsernameNotFoundException(username);
	}

}