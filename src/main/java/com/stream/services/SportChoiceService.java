package com.stream.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stream.model.dto.SportDTO;
import com.stream.model.form.SportChoiceForm;

@Service
public interface SportChoiceService {

	
	void addSportChoice(SportChoiceForm form, String userId);
	
	List<SportDTO> getUserSportChoices(String userId);
	
}
