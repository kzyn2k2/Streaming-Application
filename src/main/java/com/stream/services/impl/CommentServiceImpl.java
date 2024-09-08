package com.stream.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.stream.model.dto.CommentDTO;
import com.stream.model.entities.Comment;
import com.stream.model.form.CommentForm;
import com.stream.repositories.ChannelRepository;
import com.stream.repositories.CommentRepository;
import com.stream.repositories.UserRepository;
import com.stream.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	
	private CommentRepository commentRepository;
	private ChannelRepository channelRepository;
	private UserRepository userRepository;
	
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, ChannelRepository channelRepository, UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.channelRepository = channelRepository;
		this.userRepository = userRepository;
	}




	@Override
	public List<CommentDTO> getCommentsForChannel(Long channelId) {
		return commentRepository.getCommentsByChannel(channelId);
	}




	@Override
	public void addComment(CommentForm form, Authentication authentication) {
	
		Comment c = new Comment();
		c.setCommentText(form.getCommentText());
		c.setUser(userRepository.findByEmail(authentication.getName()).get());
		c.setChannel(channelRepository.findById(form.getChannelId()).get());
		commentRepository.save(c);
		
	}

}
