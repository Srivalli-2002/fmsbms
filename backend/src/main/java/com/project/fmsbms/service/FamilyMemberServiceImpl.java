package com.project.fmsbms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.FamilyMember;
import com.project.fmsbms.exceptions.FamilyMemberNotFoundException;
import com.project.fmsbms.repositories.FamilyMemberRepository;
 
 
@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {
	private static final Logger loggers = LoggerFactory.getLogger(FamilyMemberServiceImpl.class);
 
	@Autowired
	private FamilyMemberRepository repo;

	@Override
	public FamilyMember addMember(FamilyMember member) {
		FamilyMember fm = repo.save(member);
		loggers.info("addMember");
		return fm;
	}

	@Override
	public FamilyMember getMember(Integer id)throws FamilyMemberNotFoundException{
		Optional<FamilyMember> getMember = repo.findById(id);
		if(getMember.isPresent())
		{
			loggers.info("getMember");
			return getMember.get();
		}
		else throw new FamilyMemberNotFoundException("Family Member Not Found");
		
	}

	@Override
	public FamilyMember updateMember(FamilyMember member) throws FamilyMemberNotFoundException {
		Optional<FamilyMember> updateMember= repo.findById(member.getMemeberId());
		if(updateMember.isPresent())
		{
			loggers.info("updateMember");
			return updateMember.get();
		}
		else
		{
			 throw new FamilyMemberNotFoundException("Family Member Not Found");
		}
	}

	@Override
	public List<FamilyMember> findByFamily(Family family)throws FamilyMemberNotFoundException {
		loggers.info("findByFamily");
		List<FamilyMember> list = repo.findAllByFamily(family);
		if(!list.isEmpty()) return list;
		else
		{
			 throw new FamilyMemberNotFoundException("Family Member Not Found");
		}
	}
}