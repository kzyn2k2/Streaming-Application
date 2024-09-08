package com.stream.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stream.model.entities.SportChoice;
import com.stream.model.entities.id.SportChoiceId;

public interface SportChoiceRepository extends JpaRepository<SportChoice, SportChoiceId> {

	@Query("select sc from SportChoice sc where sc.user.id = :uid")
	List<SportChoice> getUserSportChoice(@Param("uid") String id);
}
