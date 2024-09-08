package com.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stream.services.impl.MailServiceImpl;
import com.stream.services.impl.StreamingServiceImpl;

@RestController
public class StreamingController {

	
	@Autowired
	StreamingServiceImpl streamingServiceImpl;
	
	@Autowired
	MailServiceImpl mailServiceImpl;
	
	
	@GetMapping(value = "/videos/{title}", produces = "video/mp4")
	public Resource getVideos(@PathVariable("title") String title) {
		return streamingServiceImpl.getVideo(title);
	}
	
	
	

	
}
