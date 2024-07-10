package com.project.fmsbms.entities.request;

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
public class FamilyMemberRequest {
	private String name;
	private String email;
	private String username;
	private Long phoneNumber;
	private Integer familyId;
}
