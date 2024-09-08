package com.stream.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stream.model.dto.ViewDTO;
import com.stream.model.dto.ViewTimeDTO;
import com.stream.model.entities.View;

@Service
public interface ViewTimeService {

	
	void addViewTime(View view);
	
	List<ViewTimeDTO> getUserViewLog(Long channelId, String userId);
	
}
