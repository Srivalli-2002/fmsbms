package com.project.fmsbms.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.fmsbms.entities.Family;


@Repository
public interface FamilyRepository extends CrudRepository<Family, Integer> {
	public Optional<Family> findByUsername(String username);
}
