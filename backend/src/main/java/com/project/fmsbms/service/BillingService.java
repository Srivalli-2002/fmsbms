package com.project.fmsbms.service;

import java.util.List;

import com.project.fmsbms.entities.Billing;
import com.project.fmsbms.exceptions.BillingServiceNotFoundException;
import com.project.fmsbms.exceptions.FamilyMemberNotFoundException;
import com.project.fmsbms.exceptions.MobileServiceNotFoundException;

public interface BillingService {
	
	public Billing addBill(Billing bill) throws MobileServiceNotFoundException, FamilyMemberNotFoundException;
	
	public Billing getBill(Integer billId) throws BillingServiceNotFoundException;
	
	public Billing updateBill(Billing bill) throws BillingServiceNotFoundException;
	
	public List<Billing> getAllBills() throws BillingServiceNotFoundException;

}
