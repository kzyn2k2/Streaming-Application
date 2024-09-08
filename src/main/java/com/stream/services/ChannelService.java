package com.stream.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stream.model.dto.ChannelDTO;
import com.stream.model.form.ChannelForm;

@Service
public interface ChannelService {

	
	void addChannel(ChannelForm form, MultipartFile video) throws IOException;
	
	List<ChannelDTO> getChannelBySport(Long sportId);
	
	ChannelDTO getChannelById(Long channelId);
	
}
