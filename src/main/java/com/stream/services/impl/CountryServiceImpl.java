package com.stream.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stream.model.dto.CountryDTO;
import com.stream.repositories.CountryRepository;
import com.stream.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	private CountryRepository countryRepository;
	
	@Autowired
	public CountryServiceImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}



	@Override
	public List<CountryDTO> getAllCountries() {
	
		return countryRepository.getAllCountries();
	}

}
