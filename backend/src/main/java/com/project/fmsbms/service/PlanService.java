package com.project.fmsbms.service;

import java.util.List;

import com.project.fmsbms.entities.Plan;
import com.project.fmsbms.entities.PlanType;
import com.project.fmsbms.entities.UserEntity;
import com.project.fmsbms.exceptions.PlanNotFoundException;

public interface PlanService {
	
  public Plan addPlan(Plan plan);
  
  public Plan getPlan(PlanType planType) throws PlanNotFoundException;
  
  public Plan updatePlan(Plan plan) throws PlanNotFoundException;
  
  public List<Plan> getAllPlans();

}
