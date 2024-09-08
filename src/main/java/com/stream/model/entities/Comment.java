package com.stream.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String commentText;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "channel_id")
	private Channel channel;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private User user;
	
}
