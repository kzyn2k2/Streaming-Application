package com.stream.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.stream.model.dto.ViewDTO;
import com.stream.model.entities.View;

@Service
public interface ViewService {

	
	View addView(Long channelId, Authentication authentication);
	
	List<ViewDTO> getUserViews(Authentication authentication);
	
	List<ViewDTO> getUserViews(String userId);
	
	Long getViewCountOfSport(Long sportId);
	
	
}
