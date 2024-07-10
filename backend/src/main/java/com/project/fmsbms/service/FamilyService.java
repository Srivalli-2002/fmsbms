package com.project.fmsbms.service;

import com.project.fmsbms.entities.Family;
import com.project.fmsbms.exceptions.FamilyNotFoundException;

public interface FamilyService {

	public Family addFamily(Family family);
	
	public Family updateFamily(Family family) throws FamilyNotFoundException;
	
	public Family getFamilyById(Integer familyid)throws FamilyNotFoundException ;
	
	public Family getFamilyByUsername(String username)throws FamilyNotFoundException ;
	
}
