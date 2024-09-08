package com.stream.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stream.model.entities.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByName(String name);
	
}
