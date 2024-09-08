package com.stream.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stream.model.entities.Activation;

public interface ActivationRepository extends JpaRepository<Activation, Long> {

	Optional<Activation> findByCode(String code);
	

	
}
