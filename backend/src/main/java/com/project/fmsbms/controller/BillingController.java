package com.project.fmsbms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.fmsbms.entities.Billing;
import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.request.BillingRequest;
import com.project.fmsbms.entities.request.GetBillingRequest;
import com.project.fmsbms.entities.request.GetBillingRequestById;
import com.project.fmsbms.exceptions.BillingServiceNotFoundException;
import com.project.fmsbms.exceptions.FamilyMemberNotFoundException;
import com.project.fmsbms.exceptions.FamilyNotFoundException;
import com.project.fmsbms.exceptions.MobileServiceNotFoundException;
import com.project.fmsbms.service.BillingService;
import com.project.fmsbms.service.FamilyService;


@RestController
@RequestMapping("/api/billing")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BillingController {
	private static final Logger loggers = LoggerFactory.getLogger(BillingController.class);
	
	@Autowired
	BillingService billingService;
	@Autowired
	FamilyService familyService;
	
	@PostMapping("/addbill")
	public ResponseEntity<Billing> addBill(@RequestBody BillingRequest billingRequest) throws FamilyNotFoundException, MobileServiceNotFoundException, FamilyMemberNotFoundException
	{
		Billing bill=new Billing();
		Family family= familyService.getFamilyByUsername(billingRequest.getUsername());
		bill.setFamily(family);
		Billing b= billingService.addBill(bill);
		loggers.info("addbill");
        return new ResponseEntity<>(b,HttpStatus.OK);
	}
	
	@PostMapping("/getbillbyid")
	public ResponseEntity<Billing> getBill(@RequestParam("billId") Integer billId) throws BillingServiceNotFoundException
	{
		Billing bill=billingService.getBill(billId);
		loggers.info("getbill");
        return new ResponseEntity<>(bill,HttpStatus.OK);
	}
	
	@PostMapping("/getbill")
	public ResponseEntity<Billing> getBill(@RequestBody GetBillingRequestById getBillingRequest) throws BillingServiceNotFoundException
	{
		Billing bill=billingService.getBill(getBillingRequest.getBillId());
		return new ResponseEntity<Billing>(bill, HttpStatus.OK);
	}
	
	@GetMapping("/getallbills")
	public ResponseEntity<List<Billing>> getAllBills() throws BillingServiceNotFoundException
	{
		List<Billing> bills= billingService.getAllBills();
		return new ResponseEntity<>(bills, HttpStatus.OK);
	}
	
	
	
}
