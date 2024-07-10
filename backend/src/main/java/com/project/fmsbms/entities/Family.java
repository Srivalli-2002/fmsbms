package com.project.fmsbms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "families_table")
public class Family {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer familyId;
	private String familyHeadName;
	private String username;
	private String email;
	private String address;
	private Long phoneNumber;
	@ManyToOne
	private Plan plan;
}
