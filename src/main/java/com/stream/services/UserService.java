package com.stream.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stream.exceptions.IncorrectPasswordExcpetion;
import com.stream.exceptions.UserExistsException;
import com.stream.model.dto.UserDTO;
import com.stream.model.entities.User;
import com.stream.model.form.RegistrationForm;

@Service
public interface UserService {

	User registerUser(RegistrationForm rf) throws UserExistsException;
	
	UserDTO getUserById(String id);
	
	User getUserByEmail(String email);
	
	List<UserDTO> getUsers();
	
	void resetPassword(String email, String orpass, String newpass) throws IncorrectPasswordExcpetion;
	
	void deactivateUser(String id);
	
}
