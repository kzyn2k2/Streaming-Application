package com.stream.services;

import org.springframework.stereotype.Service;

import com.stream.exceptions.ActivationException;
import com.stream.model.entities.Activation;
import com.stream.model.entities.User;

@Service
public interface ActivationService {

	void addActivation(User u, String code);
	
	String activate(String code) throws ActivationException;
	
	
}
