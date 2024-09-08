package com.stream.model.dto;

import java.util.List;

public interface ChannelDTO {

	Long getId();
	
	String getName();
	
	String getVideoName();
	
	String getDescription();
	
	SportDTO getSport();
	
	List<CommentDTO> getComments();
	
	List<ViewDTO> getViews();
	
	
}
