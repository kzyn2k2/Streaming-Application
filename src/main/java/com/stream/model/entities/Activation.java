package com.stream.model.entities;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activation implements Serializable{


	private static final long serialVersionUID = 1L;

	private static final int EXPIRATION = 60 * 24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	private String code;
	
	private LocalDateTime expire;
	
	public void setExpireDate() {
		
		
		var currentTime = LocalDateTime.now();
		
		expire = currentTime.plusMinutes(EXPIRATION);
		
		
	}

	
	
}
