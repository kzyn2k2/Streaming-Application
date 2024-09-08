package com.stream.model.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "sport_id")
	private Sport sport;
	
	private String videoName;
	
	@OneToMany(mappedBy = "channel")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "channel", fetch = FetchType.EAGER)
	private List<View> views;
	
}
