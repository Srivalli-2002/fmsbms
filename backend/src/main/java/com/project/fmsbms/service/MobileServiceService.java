package com.project.fmsbms.service;

import com.project.fmsbms.entities.FamilyMember;
import com.project.fmsbms.entities.MobileService;
import com.project.fmsbms.exceptions.MobileServiceNotFoundException;

public interface MobileServiceService {
	public MobileService addMobileService(MobileService ms);

	public MobileService getMobileService(Integer id) throws MobileServiceNotFoundException;

	public MobileService updateMobileService(MobileService ms) throws MobileServiceNotFoundException;

	public MobileService getByFamilyMember(FamilyMember familyMember) throws MobileServiceNotFoundException;
}
