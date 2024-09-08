package com.stream.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.stream.model.dto.ViewDTO;
import com.stream.model.entities.Channel;
import com.stream.model.entities.User;
import com.stream.model.entities.View;
import com.stream.repositories.ChannelRepository;
import com.stream.repositories.UserRepository;
import com.stream.repositories.ViewRepository;
import com.stream.services.ViewService;

@Service
public class ViewServiceImpl implements ViewService {

	private ChannelRepository channelRepository;
	private UserRepository userRepository;
	private ViewRepository viewRepository;
	
	@Autowired
	public ViewServiceImpl(ChannelRepository channelRepository, UserRepository userRepository, ViewRepository viewRepository) {
		this.channelRepository = channelRepository;
		this.userRepository = userRepository;
		this.viewRepository = viewRepository;
	}

	@Override
	public View addView(Long channelId, Authentication authentication) {
	
		Channel channel = channelRepository.findById(channelId).get();
		User user = userRepository.findByEmail(authentication.getName()).get();
		Optional<View> con = viewRepository.checkExist(channelId, user.getId());
		
		if(con.isEmpty()) {
			View view = new View();
			
			view.setChannel(channel);
			view.setUser(user);
			
			return viewRepository.save(view);
			
		}
		
			return con.get();
		
	}

	@Override
	public List<ViewDTO> getUserViews(Authentication authentication) {
		
		User u = userRepository.findByEmail(authentication.getName()).get();
		
		return viewRepository.getUserViews(u.getId());
	}

	@Override
	public List<ViewDTO> getUserViews(String userId) {

		return viewRepository.getUserViews(userId);
	}

	@Override
	public Long getViewCountOfSport(Long sportId) {
		return viewRepository.countViewForSport(sportId);
	}

}
