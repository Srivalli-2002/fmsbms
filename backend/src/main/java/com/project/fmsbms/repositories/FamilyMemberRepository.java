package com.project.fmsbms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.FamilyMember;

@Repository
public interface FamilyMemberRepository extends CrudRepository<FamilyMember, Integer> {
	public List<FamilyMember> findAllByFamily(Family family); 
}