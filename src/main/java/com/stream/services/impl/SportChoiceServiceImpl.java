package com.stream.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stream.model.dto.SportDTO;
import com.stream.model.entities.Sport;
import com.stream.model.entities.SportChoice;
import com.stream.model.form.SportChoiceForm;
import com.stream.repositories.SportChoiceRepository;
import com.stream.repositories.SportRepository;
import com.stream.repositories.UserRepository;
import com.stream.services.SportChoiceService;

@Service
public class SportChoiceServiceImpl implements SportChoiceService {

	private SportRepository sportRepository;
	private SportChoiceRepository sportChoiceRepository;
	private UserRepository userRepository;
	
	
	@Autowired
	public SportChoiceServiceImpl(SportRepository sportRepository, SportChoiceRepository sportChoiceRepository,
			UserRepository userRepository) {
		super();
		this.sportRepository = sportRepository;
		this.sportChoiceRepository = sportChoiceRepository;
		this.userRepository = userRepository;
	}




	@Override
	public void addSportChoice(SportChoiceForm form, String userId) {
		
		for(Long s : form.getSports()) {
			
			SportChoice sc = new SportChoice();
			Sport sport = sportRepository.findById(s).get();
			sc.setSport(sport);
			sc.setUser(userRepository.findById(userId).get());
			sportChoiceRepository.save(sc);
			
		}
	}




	@Override
	public List<SportDTO> getUserSportChoices(String userId) {
	
		List<SportChoice> scs = sportChoiceRepository.getUserSportChoice(userId);
		
		List<SportDTO> res = new ArrayList<>();
		
		for(SportChoice s : scs) {
			
			SportDTO sport = sportRepository.findSportById(s.getSport().getId()).get();
			res.add(sport);
		}
		
		return res;
	}

}
