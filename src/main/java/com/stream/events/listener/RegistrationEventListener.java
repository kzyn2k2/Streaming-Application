package com.stream.events.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.stream.events.RegisterEvent;
import com.stream.model.entities.Activation;
import com.stream.model.entities.User;
import com.stream.services.ActivationService;
import com.stream.services.MailService;
import com.stream.services.UserService;

@Component
public class RegistrationEventListener implements ApplicationListener<RegisterEvent> {


	private UserService userService;
	private MailService mailService;
	private ActivationService activationService;
	
	
	@Autowired
	public RegistrationEventListener(UserService userService, MailService mailService, ActivationService activationService) {
		
		this.userService = userService;
		this.mailService = mailService;
		this.activationService = activationService;
	}
	
	@Override
	public void onApplicationEvent(RegisterEvent event) {
		
		this.sendActivation(event.getUser());
		
	}
	
	private void sendActivation(User user) {
		
		String code = UUID.randomUUID().toString();
		
		activationService.addActivation(user, code);
		
		mailService.sendMail(user.getEmail(), code);
		
	}

}
