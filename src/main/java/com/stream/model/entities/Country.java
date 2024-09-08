package com.stream.model.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	
}
