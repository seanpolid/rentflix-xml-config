package edu.wccnet.sepolidori.exception_handling;

public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
	
}
