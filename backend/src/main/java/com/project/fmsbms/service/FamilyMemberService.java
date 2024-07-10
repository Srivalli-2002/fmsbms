package com.project.fmsbms.service;

import java.util.List;

import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.FamilyMember;
import com.project.fmsbms.exceptions.FamilyMemberNotFoundException;

 
public interface FamilyMemberService {
 
	public FamilyMember addMember (FamilyMember member);
	
	public FamilyMember getMember (Integer id) throws FamilyMemberNotFoundException;
	
	public FamilyMember updateMember (FamilyMember member) throws FamilyMemberNotFoundException;
	
	public List<FamilyMember> findByFamily(Family family)throws FamilyMemberNotFoundException;
}