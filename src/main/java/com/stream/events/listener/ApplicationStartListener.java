package com.stream.events.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.stream.model.entities.Admin;
import com.stream.model.entities.Country;
import com.stream.model.entities.User;
import com.stream.repositories.AdminRepository;
import com.stream.repositories.UserRepository;

@Component
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	
		Admin a  = new Admin();
		a.setName("admin");
		a.setPassword("admin2244");
		adminRepository.save(a);
		
		
	}

}
