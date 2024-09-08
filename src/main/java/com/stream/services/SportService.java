package com.stream.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stream.model.dto.SportDTO;

@Service
public interface SportService {

	
	List<SportDTO> getAllSports();
	
	SportDTO getSportById(Long id);
	
	List<SportDTO> getSportsWithChannel();
	
	
}
