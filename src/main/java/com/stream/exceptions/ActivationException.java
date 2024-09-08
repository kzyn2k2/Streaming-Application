package com.stream.exceptions;

public class ActivationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ActivationException() {
		
		super("Activation expired or does not exists");
		
	}
	
	
}
