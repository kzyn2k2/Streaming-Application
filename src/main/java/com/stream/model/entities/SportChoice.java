package com.stream.model.entities;

import com.stream.model.entities.id.SportChoiceId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@IdClass(SportChoiceId.class)
@NoArgsConstructor
@AllArgsConstructor
public class SportChoice {

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn
	private Sport sport;
	
	
}
