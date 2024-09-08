package com.stream.model.entities;

import org.hibernate.annotations.GenericGenerator;

import com.stream.model.entities.id.ViewId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(ViewId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class View {


	@Id
	@ManyToOne()
	@JoinColumn(name = "channel_id")
	private Channel channel;
	
	
	@Id
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
}
