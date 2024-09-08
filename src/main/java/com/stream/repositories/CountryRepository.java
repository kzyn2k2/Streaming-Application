package com.stream.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stream.model.dto.CountryDTO;
import com.stream.model.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

	@Query("select c from Country c")
	List<CountryDTO> getAllCountries();
	
}
