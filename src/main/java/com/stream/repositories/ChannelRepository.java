package com.stream.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stream.model.dto.ChannelDTO;
import com.stream.model.entities.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

	@Query("select c from Channel c where c.sport.id = :id")
	List<ChannelDTO> getChannelsBySport(@Param("id") Long id);
	
	@Query("select c from Channel c where c.id = :id")
	ChannelDTO getChannelById(@Param("id") Long id);
	
}
