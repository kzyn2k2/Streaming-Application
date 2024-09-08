package com.stream.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stream.model.dto.CountryDTO;

@Service
public interface CountryService {

	
	List<CountryDTO> getAllCountries();
	
}
