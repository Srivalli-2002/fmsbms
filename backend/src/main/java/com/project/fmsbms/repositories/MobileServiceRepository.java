package com.project.fmsbms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.fmsbms.entities.FamilyMember;
import com.project.fmsbms.entities.MobileService;

@Repository
public interface MobileServiceRepository extends CrudRepository<MobileService, Integer>{
 
	public MobileService findByFamilyMember(FamilyMember familyMember);
}
