package com.project.fmsbms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fmsbms.entities.Billing;
import com.project.fmsbms.entities.DataPlanType;
import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.FamilyMember;
import com.project.fmsbms.entities.MobileService;
import com.project.fmsbms.entities.Plan;
import com.project.fmsbms.entities.request.BuySimRequest;
import com.project.fmsbms.entities.request.FamilyId;
import com.project.fmsbms.entities.request.FamilyMemberRequest;
import com.project.fmsbms.entities.request.FamilyUsername;
import com.project.fmsbms.entities.request.GetBillingRequest;
import com.project.fmsbms.entities.request.GetFamilyMemberRequest;
import com.project.fmsbms.entities.request.MobileServiceRequest;
import com.project.fmsbms.exceptions.BillingServiceNotFoundException;
import com.project.fmsbms.exceptions.FamilyMemberNotFoundException;
import com.project.fmsbms.exceptions.FamilyNotFoundException;
import com.project.fmsbms.exceptions.PlanNotFoundException;
import com.project.fmsbms.service.BillingService;
import com.project.fmsbms.service.FamilyMemberService;
import com.project.fmsbms.service.FamilyService;
import com.project.fmsbms.service.MobileServiceService;
import com.project.fmsbms.service.PlanService;

@RestController
@RequestMapping("/api/family")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FamilyController {
	
	@Autowired
	FamilyService familyService;
	
	@Autowired
	PlanService planService;
	
	@Autowired
	BillingService billingService;
	

	@Autowired
	FamilyMemberService familyMemeberService;
	
	@Autowired
	MobileServiceService mobileService;
	
	@PostMapping("/buysim")
	public ResponseEntity<String> buySim(@RequestBody BuySimRequest req) throws FamilyNotFoundException, PlanNotFoundException
	{
		Family family= familyService.getFamilyByUsername(req.getUsername());
		family.setFamilyHeadName(req.getFamilyHeadName());
		family.setEmail(req.getEmail());
		family.setAddress(req.getAddress());
		Plan plan= planService.getPlan(req.getPlanType());
		family.setPlan(plan);
		familyService.updateFamily(family);
		String message= "Sim Purchased Successfully";
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	@PostMapping("/getallmembersbyfamilyid")
	public ResponseEntity<List<FamilyMember>> getallmembers(@RequestBody FamilyId familyid) throws FamilyNotFoundException, FamilyMemberNotFoundException{
		Family family= familyService.getFamilyById(familyid.getId());
		List<FamilyMember> list= familyMemeberService.findByFamily(family);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/getallmembersbyusername")
	public ResponseEntity<List<FamilyMember>> getallmembers(@RequestBody FamilyUsername username) throws FamilyNotFoundException, FamilyMemberNotFoundException{
		Family family= familyService.getFamilyByUsername(username.getUsername());
		List<FamilyMember> list= familyMemeberService.findByFamily(family);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/getmember")
	public ResponseEntity<FamilyMember> getFamilyMember(@RequestBody GetFamilyMemberRequest getFamilyMemberRequest) throws FamilyMemberNotFoundException
	{
		FamilyMember familyMember = familyMemeberService.getMember(getFamilyMemberRequest.getMemeberId());
		return new ResponseEntity<FamilyMember>(familyMember, HttpStatus.OK);
	}
	@PostMapping("/updatemember")
	public ResponseEntity<FamilyMember> updateFamilyMember(@RequestBody FamilyMemberRequest familyMemberRequest) throws FamilyNotFoundException, FamilyMemberNotFoundException {
		FamilyMember familyMember = new FamilyMember();
		familyMember.setName(familyMemberRequest.getName());
		familyMember.setPhoneNumber(familyMemberRequest.getPhoneNumber());
		familyMember.setEmail(familyMemberRequest.getEmail());
		familyMember.setName(familyMemberRequest.getName());
		familyMember.setFamily(familyService.getFamilyById(familyMemberRequest.getFamilyId()));
		FamilyMember fm = familyMemeberService.updateMember(familyMember);
		return new ResponseEntity<FamilyMember>(fm, HttpStatus.OK);
	}
	
	@PostMapping("/addservice") 
	public ResponseEntity<MobileService> addMobileService(@RequestBody MobileServiceRequest msr) throws FamilyMemberNotFoundException {
		MobileService ms = new MobileService();
		ms.setFamilyMember(familyMemeberService.getMember(msr.getMemberId()));
		ms.setDataPlanType(msr.getDataPlanType());
		if((DataPlanType.UNLIMITED).equals(ms.getDataPlanType())) {
			ms.setStartDate(LocalDate.now());
			ms.setDataLimit("UNLIMITED");
			ms.setMonthlyFee(50.00);
		} else if(DataPlanType.BASIC.equals(ms.getDataPlanType())) {
			ms.setStartDate(LocalDate.now());
			ms.setDataLimit("10GB");
			ms.setMonthlyFee(20.00);
		} else if(DataPlanType.PREMIUM.equals(ms.getDataPlanType())) {
			ms.setStartDate(LocalDate.now());
			ms.setDataLimit("50GB");
			ms.setMonthlyFee(40.00);
		}
		mobileService.addMobileService(ms);
		return new ResponseEntity<MobileService>(ms, HttpStatus.OK);
	}
	
	
	@PostMapping("/getbill")
	public ResponseEntity<Billing> getBill(@RequestBody GetBillingRequest getBillingRequest) throws BillingServiceNotFoundException
	{
		Billing bill=billingService.getBill(getBillingRequest.getBillId());
		return new ResponseEntity<Billing>(bill, HttpStatus.OK);
	}
}

