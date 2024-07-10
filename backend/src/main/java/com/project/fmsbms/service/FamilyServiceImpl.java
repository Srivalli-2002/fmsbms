package com.project.fmsbms.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fmsbms.entities.Family;
import com.project.fmsbms.exceptions.FamilyNotFoundException;
import com.project.fmsbms.repositories.FamilyRepository;


@Service
public class FamilyServiceImpl  implements FamilyService{
	private static final Logger loggers = LoggerFactory.getLogger(FamilyServiceImpl.class);
	
	
	@Autowired
	FamilyRepository familyrepo;
	
	@Override
	public Family addFamily(Family family) {
		
		Family familyadded=familyrepo.save(family);
		loggers.info("addFamily");
		return familyadded;
	}

	@Override
	public Family updateFamily(Family family) throws FamilyNotFoundException {
		
	Optional<Family> getfamilybyid=familyrepo.findById(family.getFamilyId());
		
		if(getfamilybyid.isPresent())
		{
		Family familyupdated = familyrepo.save(family);
		loggers.info("updateFamily");
		return familyupdated ;
		}
		else throw new FamilyNotFoundException("family not found");
		
	}

	@Override
	public Family getFamilyById(Integer familyid) throws FamilyNotFoundException {
		Optional<Family> getfamilybyid=familyrepo.findById(familyid);
		loggers.info("getFamilyById");
		if(getfamilybyid.isPresent())
		{
		return getfamilybyid.get();
		}
		else throw new FamilyNotFoundException("family not found");
	}

	@Override
	public Family getFamilyByUsername(String username) throws FamilyNotFoundException {
		Optional<Family> getfamily=familyrepo.findByUsername(username);
		loggers.info("getFamilyByUsername");
		if(getfamily.isPresent())
		{
		   return getfamily.get();
		}
		else throw new FamilyNotFoundException("family not found");
	}

}
