package com.stream.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stream.model.dto.ChannelDTO;
import com.stream.model.entities.Channel;
import com.stream.model.entities.Sport;
import com.stream.model.form.ChannelForm;
import com.stream.repositories.ChannelRepository;
import com.stream.repositories.SportRepository;
import com.stream.services.ChannelService;
import com.stream.services.StreamingService;

@Service
public class ChannelServiceImpl implements ChannelService {

	
	private SportRepository sportRepository;
	private ChannelRepository channelRepository;
	private StreamingService streamingService;
	
	
	@Autowired
	public ChannelServiceImpl(SportRepository sportRepository, ChannelRepository channelRepository,
			StreamingService streamingService) {

		this.sportRepository = sportRepository;
		this.channelRepository = channelRepository;
		this.streamingService = streamingService;
	}




	@Override
	public void addChannel(ChannelForm form, MultipartFile video) throws IOException {
		
		Channel channel = new Channel();
		channel.setName(form.getName());
		channel.setDescription(form.getDescription());
		channel.setVideoName(streamingService.saveVideo(video));
		Sport sport = sportRepository.findById(form.getSportId()).get();
		channel.setSport(sport);
		channelRepository.save(channel);
		
	}




	@Override
	public List<ChannelDTO> getChannelBySport(Long sportId) {

		return channelRepository.getChannelsBySport(sportId);
	}




	@Override
	public ChannelDTO getChannelById(Long channelId) {
		
		return channelRepository.getChannelById(channelId);
	}

}
