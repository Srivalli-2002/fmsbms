package com.project.fmsbms.service;

import java.util.List;

import com.project.fmsbms.entities.Billing;
import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.Payment;
import com.project.fmsbms.exceptions.BillingServiceNotFoundException;
import com.project.fmsbms.exceptions.PaymentServiceNotFoundException;

public interface PaymentService {
	
	public Payment addPayment(Payment payment) throws BillingServiceNotFoundException;
	
	public Payment getPayment(Integer paymentId) throws PaymentServiceNotFoundException;
	
	public List<Payment> getAllPaymentsByFamilyId(Family family) throws PaymentServiceNotFoundException;

}
