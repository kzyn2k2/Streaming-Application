package com.stream.model.dto;

import java.util.List;


public interface SportDTO {

	Long getId();
	
	String getName();
	
	String getImageName();
	
	String getDescription();
	
	List<ChannelDTO> getChannels();
}
