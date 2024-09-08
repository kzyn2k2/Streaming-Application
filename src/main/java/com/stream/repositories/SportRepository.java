package com.stream.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stream.model.dto.SportDTO;
import com.stream.model.entities.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

	@Query("select s from Sport s")
	List<SportDTO> getAllSports();
	
	@Query("select s from Sport s where s.id = :id")
	Optional<SportDTO> findSportById(@Param("id") Long id);
	
}
