package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	//add an exception handler for customer not found exception
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException e){
		
		// create customer error response
		HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
		CustomerErrorResponse error = new CustomerErrorResponse(notFoundStatus.value(), 
				e.getMessage(),System.currentTimeMillis());
		// return response entity
		
		
		return new ResponseEntity<CustomerErrorResponse>(error,notFoundStatus);
	}
	
	//add a generic exception handler
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception e){
		
		// create customer error response
		HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
		CustomerErrorResponse error = new CustomerErrorResponse(badRequestStatus.value(), 
				e.getMessage(),System.currentTimeMillis());
		// return response entity
		
		
		return new ResponseEntity<CustomerErrorResponse>(error,badRequestStatus);
	}
}
