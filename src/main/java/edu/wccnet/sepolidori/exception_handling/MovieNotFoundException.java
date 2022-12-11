package edu.wccnet.sepolidori.exception_handling;

public class MovieNotFoundException extends RuntimeException {
	
	public MovieNotFoundException(String message) {
		super(message);
	}
	
}
