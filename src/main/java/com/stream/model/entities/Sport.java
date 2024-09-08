package com.stream.model.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String name;
	
	private String imageName;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@OneToMany(mappedBy = "sport")
	private List<Channel> channels;
	
}
