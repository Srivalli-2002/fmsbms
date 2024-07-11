package com.project.fmsbms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.fmsbms.entities.Billing;
import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.request.FamilyUsername;

@Repository
public interface BillingRepository extends CrudRepository<Billing, Integer>{

	public List<Billing> findAllByFamily(Family family);
	
//	public List<Billing> getBillByUsername(FamilyUsername familyUsername);
}
