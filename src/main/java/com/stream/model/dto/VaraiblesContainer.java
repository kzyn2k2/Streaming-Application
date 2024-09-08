package com.stream.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class VaraiblesContainer {

	private List<Variables> vars = new ArrayList<>();
	
	
	public void add(Variables v) {
		
		vars.add(v);
		
	}
	
}
