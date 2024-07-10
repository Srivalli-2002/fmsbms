package com.project.fmsbms.entities.request;

import com.project.fmsbms.entities.Family;

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
public class GetFamilyMemberRequest {
	private Integer memeberId;
}
