package com.stream.model.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewTime {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "channel_id", referencedColumnName = "channel_id"),
		@JoinColumn(name = "user_id", referencedColumnName = "user_id")
		
	})
	private View view;
	
	private LocalDateTime viewedTime;
	
}
