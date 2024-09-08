package com.stream.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stream.model.dto.CommentDTO;
import com.stream.model.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("select c from Comment c where c.channel.id = :id")
	List<CommentDTO> getCommentsByChannel(@Param("id") Long channelId);
	
}
