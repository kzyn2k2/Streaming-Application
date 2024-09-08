package com.stream.services;

import org.springframework.stereotype.Service;

@Service
public interface MailService {

	
	void sendMail(String email, String code);
	
}
