package com.stream.model.dto;

public interface CommentDTO {

	
	Long getId();
	
	String getCommentText();
	
	ChannelDTO getChannel();
	
	UserDTO getUser();
	
}
