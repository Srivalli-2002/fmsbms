package com.project.fmsbms.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.fmsbms.entities.Plan;
import com.project.fmsbms.entities.PlanType;



@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer>{
	
	public Optional<Plan>  findByPlanType(PlanType planType);

}