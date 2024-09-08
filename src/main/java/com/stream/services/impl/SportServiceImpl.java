package com.stream.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stream.model.dto.SportDTO;
import com.stream.repositories.SportRepository;
import com.stream.services.SportService;

@Service
public class SportServiceImpl implements SportService {

	
	private SportRepository sportRepository;
	
	@Autowired
	public SportServiceImpl(SportRepository sportRepository) {
		this.sportRepository = sportRepository;
	}
	
	@Override
	public List<SportDTO> getAllSports() {
		return sportRepository.getAllSports();
	}

	@Override
	public SportDTO getSportById(Long id) {
		Optional<SportDTO> res = sportRepository.findSportById(id);
		
		if(res.isPresent()) {
			
			SportDTO sport = res.get();
			return sport;
			
		}
		return null;
	}

	@Override
	public List<SportDTO> getSportsWithChannel() {
		List<SportDTO> sports = sportRepository.getAllSports();
		List<SportDTO> res = new ArrayList<>();
		for(SportDTO s : sports) {
			if(!s.getChannels().isEmpty()) {
				res.add(s);
			}
		}
		return res;
	}

}
