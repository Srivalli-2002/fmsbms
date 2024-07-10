package com.project.fmsbms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.fmsbms.entities.Billing;
import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.Payment;
import com.project.fmsbms.exceptions.BillingServiceNotFoundException;
import com.project.fmsbms.exceptions.PaymentServiceNotFoundException;
import com.project.fmsbms.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	private static final Logger loggers = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	BillingService billingService;
	
	@Override
	public Payment addPayment(Payment payment) throws BillingServiceNotFoundException {
		Billing bill=payment.getBill();
		bill.setPaid(true);
		billingService.updateBill(bill);
		loggers.info("addPayment");
		return paymentRepo.save(payment);
	}

	@Override
	public Payment getPayment(Integer paymentId) throws PaymentServiceNotFoundException{
		Optional<Payment> p= paymentRepo.findById(paymentId);
		if(p.isPresent())
		{
		loggers.info("getPayment");
		return p.get();
		}
		else throw new PaymentServiceNotFoundException("Payment Not found");
	}

	@Override
	public List<Payment> getAllPaymentsByFamilyId(Family family) throws PaymentServiceNotFoundException {
		List<Payment> payments = (List<Payment>) paymentRepo.findByFamily(family);
		if(!payments.isEmpty()) return payments;
		else throw new  PaymentServiceNotFoundException("Payments Not Found Exception");
	}

}
