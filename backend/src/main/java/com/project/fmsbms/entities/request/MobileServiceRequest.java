package com.project.fmsbms.entities.request;


import com.project.fmsbms.entities.DataPlanType;

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
public class MobileServiceRequest {
	
	private Integer memberId;

	@Enumerated
	private DataPlanType dataPlanType;
}