package com.stream.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stream.exceptions.ActivationException;
import com.stream.model.entities.Activation;
import com.stream.model.entities.User;
import com.stream.repositories.ActivationRepository;
import com.stream.repositories.UserRepository;
import com.stream.services.ActivationService;

@Service
@Transactional
public class ActivationServiceImpl implements ActivationService {

	private ActivationRepository activationRepository;
	private UserRepository userRepository;
	
	@Autowired
	public ActivationServiceImpl(ActivationRepository activationRepository, UserRepository userRepository) {

		this.activationRepository = activationRepository;
		this.userRepository = userRepository;
	}



	@Override
	public void addActivation(User u, String code) {
		
		Activation a = new Activation();
		a.setCode(code);
		a.setUser(u);
		a.setExpireDate();
		activationRepository.save(a);
		
	}



	@Override
	public String activate(String code) throws ActivationException {
		Optional<Activation> res = activationRepository.findByCode(code);
		
		if(res.isPresent() && res.get().getExpire().isAfter(LocalDateTime.now())) {
			Activation a = res.get();
			User u = a.getUser();
			userRepository.activateUser(u.getId());
			a.setExpire(LocalDateTime.now());
			activationRepository.save(a);
			return u.getId();
		}else {
			
			throw new ActivationException();
		}

	}

}
