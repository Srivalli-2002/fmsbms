package com.project.fmsbms.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.fmsbms.entities.Billing;
import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.Payment;
import com.project.fmsbms.entities.request.FamilyUsername;
import com.project.fmsbms.entities.request.GetAllPaymentRequest;
import com.project.fmsbms.entities.request.GetPaymentRequest;
import com.project.fmsbms.entities.request.PaymentRequest;
import com.project.fmsbms.exceptions.BillingServiceNotFoundException;
import com.project.fmsbms.exceptions.FamilyNotFoundException;
import com.project.fmsbms.exceptions.PaymentServiceNotFoundException;
import com.project.fmsbms.service.BillingService;
import com.project.fmsbms.service.FamilyService;
import com.project.fmsbms.service.PaymentService;

@RestController
@RequestMapping("/api/payments")

@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentController {
	private static final Logger loggers = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	PaymentService paymentService;

	@Autowired
	FamilyService familyService;

	@Autowired
	BillingService billingService;

	@PostMapping("/addpayment")
	public ResponseEntity<Payment> addPayemnt(@RequestBody PaymentRequest paymentRequest)
			throws FamilyNotFoundException, BillingServiceNotFoundException {
		Payment payment = new Payment();
		Family family = familyService.getFamilyByUsername(paymentRequest.getUsername());
		payment.setFamily(family);
		Billing bill = billingService.getBill(paymentRequest.getBillId());
		payment.setBill(bill);
		payment.setAmout(bill.getAmount());
		payment.setPaymentDate(LocalDate.now());
		payment.setPaymentMethod(paymentRequest.getPaymentMethod());
		Payment p = paymentService.addPayment(payment);
		loggers.info("addpayment");
		return new ResponseEntity<Payment>(p, HttpStatus.OK);
	}

	@PostMapping("/getpaymentbyid")
	public ResponseEntity<Payment> getPayemnt(@RequestParam("paymentId") Integer paymentId)
			throws PaymentServiceNotFoundException {
		Payment payment = paymentService.getPayment(paymentId);
		loggers.info("getpayment");
		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}

	@PostMapping("/getpayment")
	public ResponseEntity<Payment> getPayment(@RequestBody GetPaymentRequest getPaymentRequest)
			throws PaymentServiceNotFoundException {
		Payment payment = paymentService.getPayment(getPaymentRequest.getPaymentId());
		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}

	@PostMapping("/getallpaymentsbyusername")
	public ResponseEntity<List<Payment>> getAllPayments(@RequestBody GetAllPaymentRequest getAllPaymentRequest)
			throws PaymentServiceNotFoundException, FamilyNotFoundException {
		Family family = familyService.getFamilyByUsername(getAllPaymentRequest.getUsername());
		List<Payment> payments = paymentService.getAllPaymentsByFamilyId(family);
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}

	@PostMapping("/getbillbyfamilyusername")
	public ResponseEntity<List<Billing>> getBill(@RequestBody FamilyUsername familyUsername)
			throws BillingServiceNotFoundException, FamilyNotFoundException {
		List<Billing> bill = billingService.getBillByUsername(familyUsername.getUsername());
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}

}
