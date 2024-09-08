package com.stream.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.stream.model.dto.CommentDTO;
import com.stream.model.form.CommentForm;

@Service
public interface CommentService {

	
	List<CommentDTO> getCommentsForChannel(Long channelId);
	
	void addComment(CommentForm form, Authentication authentication);
	
}
