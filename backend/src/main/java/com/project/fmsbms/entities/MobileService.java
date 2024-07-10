package com.project.fmsbms.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "mobile_services_table")
public class MobileService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer serviceId;
	@OneToOne
	private FamilyMember familyMember;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private DataPlanType dataPlanType;
	
	private String dataLimit;
	private Double monthlyFee;
	private LocalDate startDate;
	private LocalDate endDate;
}
