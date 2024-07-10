package com.project.fmsbms.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fmsbms.entities.DataPlanType;
import com.project.fmsbms.entities.ERole;
import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.FamilyMember;
import com.project.fmsbms.entities.MobileService;
import com.project.fmsbms.entities.Plan;
import com.project.fmsbms.entities.PlanType;
import com.project.fmsbms.entities.Role;
import com.project.fmsbms.entities.UserEntity;
import com.project.fmsbms.entities.request.AllocatePhoneNumberRequest;
import com.project.fmsbms.entities.request.GetFamilyMemberRequest;
import com.project.fmsbms.entities.request.PlanRequest;
import com.project.fmsbms.entities.request.UpdateUserRole;
import com.project.fmsbms.exceptions.FamilyMemberNotFoundException;
import com.project.fmsbms.exceptions.FamilyNotFoundException;
import com.project.fmsbms.exceptions.PlanNotFoundException;
import com.project.fmsbms.exceptions.RoleNotFoundException;
import com.project.fmsbms.service.FamilyMemberService;
import com.project.fmsbms.service.FamilyService;
import com.project.fmsbms.service.MobileServiceService;
import com.project.fmsbms.service.PlanService;
import com.project.fmsbms.service.RoleService;
import com.project.fmsbms.service.UserEntityService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
	
	@Autowired
	UserEntityService userEntityService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	FamilyService familyService;
	
	@Autowired
	FamilyMemberService familyMemberService;
	
	@Autowired
	PlanService planService;
	
	@Autowired
	MobileServiceService mobileServiceService;
	
	@GetMapping("/getallusers")
	public ResponseEntity<List<UserEntity>> getAllUsers()
	{
		List<UserEntity> users= userEntityService.getAllUserEntities();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PostMapping("/updaterole")
	public ResponseEntity<String> updateUserRole(@RequestBody UpdateUserRole req) throws RoleNotFoundException
	{
		Optional<Role> role= roleService.findRoleByName(req.getRole());
		if(req.getRole()==ERole.ROLE_FAMILY_HEAD)
		{
			Family family= new Family();
			family.setUsername(req.getUsername());
			familyService.addFamily(family);
		}
		String message = userEntityService.updateRole(req.getUsername(), role.get());
		
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	@PostMapping("/allocatephonenumber")
	public ResponseEntity<String> allocatePhoneNumber(@RequestBody AllocatePhoneNumberRequest req) throws FamilyNotFoundException, FamilyMemberNotFoundException
	{
		Family family =familyService.getFamilyByUsername(req.getUsername());
		family.setPhoneNumber(req.getPhoneNumber());
		Integer totalMembers= family.getPlan().getTotalMembers();
		FamilyMember familyHead=new FamilyMember();
		familyHead.setFamily(family);
		familyHead.setName(family.getFamilyHeadName());
		familyHead.setEmail(family.getEmail());
		familyHead.setPhoneNumber(family.getPhoneNumber());
		familyMemberService.addMember(familyHead);
		for(Integer i=1;i<=totalMembers;i++)
		{
			FamilyMember member= new FamilyMember();
			member.setFamily(family);
			member.setPhoneNumber(family.getPhoneNumber()+i);
			familyMemberService.addMember(member);
		}
		MobileService mobileService= new MobileService(); 
		if(family.getPlan().getPlanType()==PlanType.BASIC)
		{
			mobileService.setFamilyMember(familyHead);
			mobileService.setMonthlyFee(family.getPlan().getAmount());
			mobileService.setDataLimit("UNLIMITED");
			mobileService.setStartDate(LocalDate.now());
			mobileService.setDataPlanType(DataPlanType.UNLIMITED);
			mobileServiceService.addMobileService(mobileService);
			for(Integer i=1;i<=totalMembers;i++)
			{
				MobileService mobileServiceMember= new MobileService(); 
				mobileServiceMember.setStartDate(LocalDate.now());
				mobileServiceMember.setDataLimit("10GB");
				mobileServiceMember.setDataPlanType(DataPlanType.BASIC);
				mobileServiceMember.setMonthlyFee(20.00);
				FamilyMember member=familyMemberService.getMember(familyHead.getMemeberId()+i);
				mobileServiceMember.setFamilyMember(member);
				mobileServiceService.addMobileService(mobileServiceMember);
			}
		}
		else
		{
			mobileService.setFamilyMember(familyHead);
			mobileService.setMonthlyFee(family.getPlan().getAmount());
			mobileService.setDataLimit("UNLIMITED");
			mobileService.setStartDate(LocalDate.now());
			mobileService.setDataPlanType(DataPlanType.UNLIMITED);
			mobileServiceService.addMobileService(mobileService);
			for(Integer i=1;i<=totalMembers;i++)
			{
				MobileService mobileServiceMember= new MobileService(); 
				mobileServiceMember.setStartDate(LocalDate.now());
				mobileServiceMember.setDataLimit("50GB");
				mobileServiceMember.setDataPlanType(DataPlanType.PREMIUM);
				mobileServiceMember.setMonthlyFee(40.00);
				FamilyMember member=familyMemberService.getMember(familyHead.getMemeberId()+i);
				mobileServiceMember.setFamilyMember(member);
				mobileServiceService.addMobileService(mobileServiceMember);
			}
		}
		
		
		
		String message= "Number Allocated Successfully!";
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	@PostMapping("/addplan")
	public ResponseEntity<Plan> addPlan(@RequestBody PlanRequest req)
	{
		Plan plan = new Plan();
		plan.setPlanType(req.getPlanType());
		plan.setTotalMembers(req.getTotalMembers());
		plan.setAmount(req.getAmount());
		Plan addedPlan= planService.addPlan(plan);
		return new ResponseEntity<>(addedPlan,HttpStatus.OK);
	}
	
	@GetMapping("/getallplans")
	public ResponseEntity<List<Plan>> getAllPlans()
	{
		List<Plan> plans= planService.getAllPlans();
		return new ResponseEntity<>(plans,HttpStatus.OK);
	}
}
