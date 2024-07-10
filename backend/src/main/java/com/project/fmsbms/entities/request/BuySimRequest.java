package com.project.fmsbms.entities.request;

import com.project.fmsbms.entities.PlanType;

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
public class BuySimRequest {
	
	private String username;
	private String familyHeadName;
	private String email;
	private String address;
	private PlanType planType;


}