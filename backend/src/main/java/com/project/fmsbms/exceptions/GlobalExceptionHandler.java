package com.project.fmsbms.exceptions;

//Imports necessary for the GlobalExceptionHandler class
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
* Global exception handler for handling various exceptions throughout the application.
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<String> handleRoleNotFoundException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BillingServiceNotFoundException.class)
	public ResponseEntity<String> handleBillingServiceNotFoundException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(FamilyMemberNotFoundException.class)
	public ResponseEntity<String> handleFamilyMemberNotFoundException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(FamilyNotFoundException.class)
	public ResponseEntity<String> handleFamilyNotFoundException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PaymentServiceNotFoundException.class)
	public ResponseEntity<String> handlePaymentServiceNotFoundException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MobileServiceNotFoundException.class)
	public ResponseEntity<String> handleMobileServiceNotFoundException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}