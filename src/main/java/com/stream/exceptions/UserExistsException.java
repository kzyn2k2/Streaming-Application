package com.stream.exceptions;

public class UserExistsException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public UserExistsException() {
		
		super("User already exists");
	}
}
