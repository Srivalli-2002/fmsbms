package com.project.fmsbms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fmsbms.entities.Plan;
import com.project.fmsbms.entities.PlanType;
import com.project.fmsbms.entities.UserEntity;
import com.project.fmsbms.exceptionmessages.QueryMapper;
import com.project.fmsbms.exceptions.PlanNotFoundException;
import com.project.fmsbms.repositories.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepository repo;
	
	@Override
	public Plan addPlan(Plan plan) {
		Plan addedPlan= repo.save(plan);
		return addedPlan;
	}

	@Override
	public Plan getPlan(PlanType planType) throws PlanNotFoundException {
		Optional<Plan> getPlan= repo.findByPlanType(planType);
		if(getPlan.isPresent())
		{
			return getPlan.get();
		}
		else throw new PlanNotFoundException("Plan Not Found");
	}

	@Override
	public Plan updatePlan(Plan plan) throws PlanNotFoundException {
		Optional<Plan> getPlan= repo.findById(plan.getPlanId());
		if(getPlan.isPresent())
		{
			Plan updatedPlan= repo.save(plan);
			return updatedPlan;
		}
		else throw new PlanNotFoundException("Plan Not Found");
	}

	@Override
	public List<Plan> getAllPlans() {
		List<Plan> allPlans = (List<Plan>) repo.findAll();
		return allPlans;
	}

	

}
