package com.stream.model.entities.id;

import java.io.Serializable;

import com.stream.model.entities.Sport;
import com.stream.model.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportChoiceId implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	
	private Sport sport;
	
}
