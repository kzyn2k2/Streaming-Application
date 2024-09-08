package com.stream.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stream.model.dto.ViewDTO;
import com.stream.model.dto.ViewTimeDTO;
import com.stream.model.entities.View;
import com.stream.model.entities.ViewTime;
import com.stream.repositories.ViewTimeRepository;
import com.stream.services.ViewTimeService;

@Service
public class ViewTimeServiceImpl implements ViewTimeService {

	
	private ViewTimeRepository viewTimeRepository;
	
	@Autowired
	public ViewTimeServiceImpl(ViewTimeRepository viewTimeRepository) {
		this.viewTimeRepository = viewTimeRepository;
	}




	@Override
	public void addViewTime(View view) {

		ViewTime vt = new ViewTime();
		vt.setView(view);
		vt.setViewedTime(LocalDateTime.now());
		viewTimeRepository.save(vt);
	}




	@Override
	public List<ViewTimeDTO> getUserViewLog(Long channelId, String userId) {
		return viewTimeRepository.getUserViewLogOfChannel(channelId, userId);
	}

}
