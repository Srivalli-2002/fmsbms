package com.project.fmsbms.entities;



import java.time.LocalDate;

import jakarta.persistence.Entity;
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
@Table(name="billing_table")
public class Billing {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer billId;
	
	@OneToOne
	private Family family;
	
	private LocalDate billingPeriodStart;
	private LocalDate billingPeriodEnd;
	private Double amount;
	private Boolean paid;
	private LocalDate dueDate;
}
