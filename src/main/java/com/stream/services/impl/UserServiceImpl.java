package com.stream.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stream.exceptions.IncorrectPasswordExcpetion;
import com.stream.exceptions.UserExistsException;
import com.stream.model.dto.UserDTO;
import com.stream.model.entities.Country;
import com.stream.model.entities.User;
import com.stream.model.form.RegistrationForm;
import com.stream.repositories.CountryRepository;
import com.stream.repositories.UserRepository;
import com.stream.services.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	
	private UserRepository userRepository;
	private CountryRepository countryRepository;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, CountryRepository countryRepository, PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.countryRepository = countryRepository;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	@Override
	public User registerUser(RegistrationForm rf) throws UserExistsException {
		
		
		if(userRepository.findByEmail(rf.getEmail()).isPresent()) {
			throw new UserExistsException();
		}
		
		User u = new User();
		
		u.setActivated(false);
		u.setEmail(rf.getEmail());
		u.setPassword(passwordEncoder.encode(rf.getPassword() ));
		Country c = countryRepository.findById(rf.getCountryId()).get();
		u.setCountry(c);
		u.setRole("USER");
		u.setAccountName(rf.getAccountName());
		
		return userRepository.save(u);
		
		
		
	}

	@Override
	public UserDTO getUserById(String id) {
		
		Optional<UserDTO> res = userRepository.findUserById(id);
		
		if(res.isPresent()) {
			
			return res.get();
		}
		
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		
		Optional<User> res =  userRepository.findByEmail(email);
		
		if (res.isPresent()) {
			
			return res.get();
		}
		
		return null;
	}

	@Override
	public List<UserDTO> getUsers() {
		return userRepository.findAllUsers();
	}

	@Override
	public void resetPassword(String email, String orpass, String newpass) throws IncorrectPasswordExcpetion {
		
		User u = userRepository.findByEmail(email).get();
		if(passwordEncoder.matches(orpass, u.getPassword())) {
			userRepository.changePassword(u.getId(), passwordEncoder.encode(newpass));
		}else {
			throw new IncorrectPasswordExcpetion();
		}
		
		
		
		
	}

	@Override
	public void deactivateUser(String id) {
		
		User u = userRepository.findById(id).get();
		
		u.setActivated(false);
		
		userRepository.save(u);
		
	}

}
