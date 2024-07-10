package com.project.fmsbms.entities.request;

import com.project.fmsbms.entities.PlanType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlanRequest {

	
	@Enumerated(EnumType.STRING)
	private PlanType planType;
	
	private Integer totalMembers;
	
	private Double amount;
	
}