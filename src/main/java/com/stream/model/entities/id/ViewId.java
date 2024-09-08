package com.stream.model.entities.id;

import java.io.Serializable;

import com.stream.model.entities.Channel;
import com.stream.model.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewId implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Channel channel;
	
	private User user;
	
}
