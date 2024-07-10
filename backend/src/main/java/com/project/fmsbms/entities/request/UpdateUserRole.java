package com.project.fmsbms.entities.request;

import com.project.fmsbms.entities.ERole;

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
public class UpdateUserRole {
	
	private String username;
	@Enumerated
	private ERole role;

}
