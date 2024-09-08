package com.stream.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stream.model.dto.ViewDTO;
import com.stream.model.entities.View;
import com.stream.model.entities.id.ViewId;

public interface ViewRepository extends JpaRepository<View, ViewId> {


	@Query("select v from View v where v.channel.id = :chanid and v.user.id = :uid")
	Optional<View> checkExist(@Param("chanid") Long channelId, @Param("uid") String userId);
	
	@Query("select v from View v where v.user.id = :uid")
	List<ViewDTO> getUserViews(@Param("uid") String userId);
	
	@Query("select COUNT(v) from View v where v.channel.sport.id = :sid")
	Long countViewForSport(@Param("sid") Long sportId);
	
	@Query("select COUNT(v) from View v where v.channel.id = :chanid")
	Long countViewForChannel(@Param("chanid") Long channelId);
	
}
