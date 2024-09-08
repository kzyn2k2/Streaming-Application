package com.stream.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stream.model.dto.ViewTimeDTO;
import com.stream.model.entities.ViewTime;

public interface ViewTimeRepository extends JpaRepository<ViewTime, String> {

	
	
	@Query("select vt from ViewTime vt where vt.view.channel.id = :chanid and vt.view.user.id = :uid")
	List<ViewTimeDTO> getUserViewLogOfChannel(@Param("chanid") Long channelId,@Param("uid") String userId);
}
